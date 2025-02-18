package cn.stronger.we.leaf.service;

import cn.hutool.core.collection.CollUtil;
import cn.stronger.we.commons.exception.CustomException;
import cn.stronger.we.commons.framework.RestResult;
import cn.stronger.we.commons.utils.JsonTools;
import cn.stronger.we.commons.utils.StringTools;
import cn.stronger.we.commons.validator.ParamCheck;
import cn.stronger.we.leaf.config.NumberTools;
import cn.stronger.we.leaf.constants.BambooLeafConstants;
import cn.stronger.we.leaf.constants.BambooLeafResultCode;
import cn.stronger.we.leaf.framework.dto.BambooLeafConfigDTO;
import cn.stronger.we.leaf.mapper.BambooLeafConfigMapper;
import cn.stronger.we.leaf.mapper.BizLeafRelationMapper;
import cn.stronger.we.leaf.mapper.entity.BambooLeafConfig;
import cn.stronger.we.leaf.mapper.entity.BizLeafRelation;
import cn.stronger.we.leaf.rest.dto.BambooLeafConfigQuery;
import cn.stronger.we.leaf.rest.dto.BizLeafRelationQuery;
import cn.stronger.we.leaf.rest.dto.BizLeafRelationSaveDTO;
import cn.stronger.we.leaf.rest.vo.BambooLeafConfigTableVO;
import cn.stronger.we.leaf.rest.vo.BizLeafRelationTableVO;
import cn.stronger.we.redis.ICustomRedisTemplate;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @class BizLeafRelationServiceImpl
 * @department Platform Center
 * @date 2023-08-24 16:32
 */
@Slf4j
@Service
public class BizLeafRelationServiceImpl implements BizLeafRelationServiceI {

    @Resource
    private BizLeafRelationMapper mapper;

    @Resource
    private BambooLeafConfigMapper bambooLeafConfigMapper;

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private ICustomRedisTemplate customRedisTemplate;

    private final static String RELATION_ADD_LOCK = "pf:lock:relation_add_";

    private final static String RELATION_UPDATE_LOCK = "pf:lock:relation_update_";

    private final static String RELATION_DELETE_LOCK = "pf:lock:relation_delete_";

    @Override
    public BambooLeafConfigDTO getByBizCode(String bizCode) {
        String cacheKey = redisCacheKey(bizCode);
        String configJson = customRedisTemplate.get(cacheKey);
        if (StringTools.isNotEmpty(configJson)) {
            BambooLeafConfigDTO bambooLeafConfigDTO = JsonTools.getObject(configJson, BambooLeafConfigDTO.class);
            customRedisTemplate.expire(cacheKey, ICustomRedisTemplate.MIN_5);
            return bambooLeafConfigDTO;
        }
        BambooLeafConfigDTO bambooLeafConfigDTO = mapper.selectConfigByBizCode(bizCode);
        ParamCheck.notNull(bambooLeafConfigDTO, BambooLeafResultCode.CONFIG_IS_NOT_EXIST);
        customRedisTemplate.set(cacheKey, JsonTools.toJson(bambooLeafConfigDTO), ICustomRedisTemplate.MIN_5);
        return bambooLeafConfigDTO;
    }

    @Override
    public RestResult<PageInfo<BizLeafRelationTableVO>> page(BizLeafRelationQuery query) {
        PageHelper.clearPage();
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<BizLeafRelationTableVO> list = mapper.list(query);
        PageInfo<BizLeafRelationTableVO> pageInfo = new PageInfo<>(list);
        if (CollUtil.isNotEmpty(pageInfo.getList())) {
            for (BizLeafRelationTableVO bambooLeafConfigTableVO : pageInfo.getList()) {
                bambooLeafConfigTableVO.setFormatStyle(NumberTools.formatStyle(bambooLeafConfigTableVO.getFormatStyle(), bambooLeafConfigTableVO.getStartNumber()));
            }
        }
        return RestResult.success(pageInfo);
    }

    @Override
    public RestResult<?> listAll() {
        List<String> list = mapper.listAll();
        return RestResult.success(list);
    }

    @Override
    public RestResult<List<BambooLeafConfigTableVO>> ruleSelected() {
        List<BambooLeafConfigTableVO> list = bambooLeafConfigMapper.list(new BambooLeafConfigQuery());
        if (CollUtil.isNotEmpty(list)) {
            for (BambooLeafConfigTableVO bambooLeafConfigTableVO : list) {
                bambooLeafConfigTableVO.setFormatStyle(NumberTools.formatStyle(bambooLeafConfigTableVO.getFormatStyle(), bambooLeafConfigTableVO.getStartNumber()));
            }
        }
        return RestResult.success(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResult<?> add(BizLeafRelationSaveDTO dto) {
        // 获取唯一号作为锁标识
        String innerTradeNum = dto.getBizCode();
        // 上锁，防止重放
        RLock lock = redissonClient.getLock(RELATION_ADD_LOCK + innerTradeNum);
        try {
            // 上锁
            boolean tryLock = lock.tryLock(3, TimeUnit.SECONDS);
            if (tryLock) {
                // 验证业务规则是否已存在
                BizLeafRelation bizLeafRelation = mapper.selectByBizCode(dto.getBizCode());
                ParamCheck.isNull(bizLeafRelation, BambooLeafResultCode.BIZ_CONFIG_IS_EXIST);
                // 验证关联规则是否已存在
                BambooLeafConfig config = bambooLeafConfigMapper.selectByRuleCode(dto.getRuleCode());
                ParamCheck.notNull(config, BambooLeafResultCode.CONFIG_IS_NOT_EXIST);
                // 执行保存
                bizLeafRelation = new BizLeafRelation();
                bizLeafRelation.setBizCode(dto.getBizCode());
                bizLeafRelation.setRuleCode(config.getRuleCode());
                mapper.insert(bizLeafRelation);
                return RestResult.success();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("[redissonClient] Redis连接出现异常", e);
        } catch (CustomException e) {
            return RestResult.exception(e);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            // 解锁
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return RestResult.exception();
    }

    @Override
    public RestResult<?> update(BizLeafRelationSaveDTO dto) {
        // 获取唯一号作为锁标识
        String innerTradeNum = dto.getBizCode();
        // 上锁，防止重放
        RLock lock = redissonClient.getLock(RELATION_UPDATE_LOCK + innerTradeNum);
        try {
            // 上锁
            boolean tryLock = lock.tryLock(3, TimeUnit.SECONDS);
            if (tryLock) {
                // 验证业务规则是否已存在
                BizLeafRelation bizLeafRelation = mapper.selectByBizCode(dto.getBizCode());
                ParamCheck.notNull(bizLeafRelation, BambooLeafResultCode.BIZ_CONFIG_IS_NOT_EXIST);
                // 验证关联规则是否已存在
                BambooLeafConfig config = bambooLeafConfigMapper.selectByRuleCode(dto.getRuleCode());
                ParamCheck.notNull(config, BambooLeafResultCode.CONFIG_IS_NOT_EXIST);
                // 执行更新
                bizLeafRelation.setRuleCode(config.getRuleCode());
                mapper.updateById(bizLeafRelation);
                customRedisTemplate.delete(redisCacheKey(bizLeafRelation.getBizCode()));
                return RestResult.success();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("[redissonClient] Redis连接出现异常", e);
        } catch (CustomException e) {
            return RestResult.exception(e);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            // 解锁
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return RestResult.exception();
    }

    @Override
    public RestResult<?> delete(BizLeafRelationSaveDTO dto) {
        // 获取唯一号作为锁标识
        String innerTradeNum = dto.getBizCode();
        // 上锁，防止重放
        RLock lock = redissonClient.getLock(RELATION_DELETE_LOCK + innerTradeNum);
        try {
            // 上锁
            boolean tryLock = lock.tryLock(3, TimeUnit.SECONDS);
            if (tryLock) {
                // 验证业务规则是否已存在
                BizLeafRelation bizLeafRelation = mapper.selectByBizCode(dto.getBizCode());
                ParamCheck.notNull(bizLeafRelation, BambooLeafResultCode.BIZ_CONFIG_IS_NOT_EXIST);
                // 验证关联规则是否已存在
                BambooLeafConfig config = bambooLeafConfigMapper.selectByRuleCode(dto.getRuleCode());
                ParamCheck.notNull(config, BambooLeafResultCode.CONFIG_IS_NOT_EXIST);
                // 执行删除
                mapper.deleteById(bizLeafRelation);
                customRedisTemplate.delete(redisCacheKey(bizLeafRelation.getBizCode()));
                return RestResult.success();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("[redissonClient] Redis连接出现异常", e);
        } catch (CustomException e) {
            return RestResult.exception(e);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            // 解锁
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return RestResult.exception();
    }

    @Override
    public void cleanCache(String ruleCode) {
        // 根据ruleCode查询关联业务配置，并清楚其缓存
        List<BizLeafRelation> list = mapper.selectByRuleCode(ruleCode);
        if (CollUtil.isNotEmpty(list)) {
            for (BizLeafRelation bizLeafRelation : list) {
                customRedisTemplate.delete(redisCacheKey(bizLeafRelation.getBizCode()));
            }
        }
    }

    @Override
    public List<BizLeafRelation> selectListByRuleCode(String ruleCode) {
        return mapper.selectByRuleCode(ruleCode);
    }

    /**
     * redis缓存Key生成
     *
     * @param keyword keyword
     * @return {@link String }
     */
    private String redisCacheKey(String keyword) {
        return BambooLeafConstants.BAMBOO_LEAF_REDIS_KEY + "config:" + keyword;
    }
}
