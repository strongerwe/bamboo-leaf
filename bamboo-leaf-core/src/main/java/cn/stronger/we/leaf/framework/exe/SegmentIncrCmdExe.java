package cn.stronger.we.leaf.framework.exe;

import cn.stronger.we.commons.exception.CustomException;
import cn.stronger.we.commons.framework.Exe;
import cn.stronger.we.leaf.config.NumberTools;
import cn.stronger.we.leaf.constants.BambooLeafResultCode;
import cn.stronger.we.leaf.constants.ResetRuleEnums;
import cn.stronger.we.leaf.framework.LeafContext;
import cn.stronger.we.leaf.framework.dto.BambooLeafConfigDTO;
import cn.stronger.we.leaf.framework.dto.LeafAllocCreateDTO;
import cn.stronger.we.leaf.framework.dto.NumberRuleDTO;
import cn.stronger.we.leaf.framework.segment.Result;
import cn.stronger.we.leaf.framework.segment.SegmentService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description 序列化自增
 * @class SegmentIncrCmdExe
 * @department Platform Center
 * @date 2023-08-15 17:09
 */
@Slf4j
@Component(value = "SegmentIncrCmdExe")
public class SegmentIncrCmdExe implements Exe<NumberRuleDTO, String> {

    private final static long MAX_RETRY_NUM = 10;

//    private final static long STEP_MULTIPLE = 1000;

    @Value("${leaf.step.multiple:1000}")
    private Long stepMultiple;
    @Resource
    private SegmentService segmentService;

    @Resource
    private RedissonClient redissonClient;

    @Override
    public String execute(NumberRuleDTO numberRuleDTO) {
        BambooLeafConfigDTO leafConfig = LeafContext.getInput(BambooLeafConfigDTO.class);
        // 生成Incr Key
        String dateStr = ResetRuleEnums.formatKey(leafConfig.getResetRule());
        String incrKey = leafConfig.getRuleCode() + ":" + dateStr;
        Result result = segmentService.getId(incrKey);
        int tg = 0;
        while (result.getId() < 0) {
            // 大于重试次数抛出异常 避免死循环
            if (tg > MAX_RETRY_NUM) {
                throw new CustomException(BambooLeafResultCode.SEGMENT_ERROR);
            }
            tg++;
            RLock lock = redissonClient.getLock(incrKey);
            try {
                if (lock.tryLock(30, TimeUnit.SECONDS)) {
                    LeafAllocCreateDTO dto = new LeafAllocCreateDTO();
                    dto.setIncr(leafConfig.getIncrRule());
                    dto.setKey(incrKey);
                    // 步数默认为自增的STEP_MULTIPLE倍
                    dto.setStep(leafConfig.getIncrRule() * stepMultiple);
                    dto.setMaxId(leafConfig.getStartNumber());
                    dto.setDescription(leafConfig.getBizCode());
                    dto.setDateString(dateStr);
                    segmentService.getByKey(dto);
                }
            } catch (InterruptedException e) {
                log.error("系统异常! 请检查调用和系统日志[分布式锁异常]", e);
            } finally {
                if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
            result = segmentService.getId(incrKey);
        }
        long newId = result.getId();
        LeafContext.appendNewNumber(NumberTools.complementZero(leafConfig.getSerialLength(), newId));
        return null;
    }

    @Override
    public void validate(NumberRuleDTO numberRuleDTO) {

    }
}
