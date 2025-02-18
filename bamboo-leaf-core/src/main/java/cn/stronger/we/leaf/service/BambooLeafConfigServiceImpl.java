package cn.stronger.we.leaf.service;

import cn.hutool.core.collection.CollUtil;
import cn.stronger.we.commons.exception.CustomException;
import cn.stronger.we.commons.framework.RestResult;
import cn.stronger.we.commons.framework.ResultErrCodeI;
import cn.stronger.we.commons.utils.StringTools;
import cn.stronger.we.commons.validator.ParamCheck;
import cn.stronger.we.leaf.config.NumberTools;
import cn.stronger.we.leaf.constants.BambooLeafConstants;
import cn.stronger.we.leaf.constants.BambooLeafResultCode;
import cn.stronger.we.leaf.mapper.BambooLeafConfigMapper;
import cn.stronger.we.leaf.mapper.entity.BambooLeafConfig;
import cn.stronger.we.leaf.mapper.entity.BizLeafRelation;
import cn.stronger.we.leaf.rest.dto.BambooLeafConfigFormatDTO;
import cn.stronger.we.leaf.rest.dto.BambooLeafConfigQuery;
import cn.stronger.we.leaf.rest.dto.BambooLeafConfigSaveDTO;
import cn.stronger.we.leaf.rest.vo.BambooLeafConfigDetailsVO;
import cn.stronger.we.leaf.rest.vo.BambooLeafConfigTableVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description 编码规则接口
 * @class BambooLeafConfigServiceImpl
 * @department Platform Center
 * @date 2023-08-12 15:49
 */
@Service
public class BambooLeafConfigServiceImpl implements BambooLeafConfigServiceI {

    @Resource
    private BambooLeafConfigMapper mapper;

    @Resource
    private BizLeafRelationServiceI relationServiceI;

    @Override
    public RestResult<PageInfo<BambooLeafConfigTableVO>> page(BambooLeafConfigQuery query) {
        PageHelper.clearPage();
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<BambooLeafConfigTableVO> list = mapper.list(query);
        PageInfo<BambooLeafConfigTableVO> pageInfo = new PageInfo<>(list);
        if (CollUtil.isNotEmpty(pageInfo.getList())) {
            for (BambooLeafConfigTableVO bambooLeafConfigTableVO : pageInfo.getList()) {
                bambooLeafConfigTableVO.setFormatStyle(NumberTools.formatStyle(bambooLeafConfigTableVO.getFormatStyle(), bambooLeafConfigTableVO.getStartNumber()));
            }
        }
        return RestResult.success(pageInfo);
    }

    @Override
    public RestResult<BambooLeafConfigDetailsVO> details(String ruleCode) {
        BambooLeafConfig config = mapper.selectByRuleCode(ruleCode);
        ParamCheck.notNull(config, BambooLeafResultCode.CONFIG_IS_NOT_EXIST);
        BambooLeafConfigDetailsVO vo = new BambooLeafConfigDetailsVO();
        vo.setId(config.getId());
        vo.setRuleName(config.getRuleName());
        vo.setRuleCode(config.getRuleCode());
        vo.setIncrRule(config.getIncrRule());
        vo.setSerialLength(config.getSerialLength());
        vo.setFormats(formats(config.getFormat()));
        vo.setFormatStyle(NumberTools.formatStyle(config.getFormat(), config.getStartNumber()));
        vo.setRemark(config.getRemark());
        vo.setCreateTime(config.getCreateTime());
        vo.setUpdateTime(config.getUpdateTime());
        vo.setResetRule(String.valueOf(config.getResetRule()));
        vo.setStartNumber(config.getStartNumber());
        return RestResult.success(vo);
    }

    @Override
    public RestResult<?> add(BambooLeafConfigSaveDTO dto) {
        BambooLeafConfig config = mapper.selectByRuleName(dto.getRuleName());
        ParamCheck.isNull(config, BambooLeafResultCode.CONFIG_IS_EXIST);
        config = new BambooLeafConfig();
        config.setRuleName(dto.getRuleName());
        config.setRuleCode(StringTools.newUuid());
        config.setFormat(format(dto.getFormats()));
        config.setCreateTime(new Date());
        config.setIncrRule(dto.getIncrRule());
        config.setRemark(dto.getRemark());
        config.setResetRule(dto.getResetRule());
        config.setStartNumber(dto.getStartNumber());
        config.setUpdateTime(new Date());
        config.setSerialLength(dto.getSerialLength());
        mapper.insert(config);
        return RestResult.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResult<?> update(BambooLeafConfigSaveDTO dto) {
        BambooLeafConfig config = mapper.selectById(dto.getId());
        ParamCheck.notNull(config, BambooLeafResultCode.CONFIG_IS_NOT_EXIST);
        try {
            config.setRuleName(dto.getRuleName());
            config.setFormat(format(dto.getFormats()));
            config.setIncrRule(dto.getIncrRule());
            config.setRemark(dto.getRemark());
            config.setStartNumber(dto.getStartNumber());
            config.setUpdateTime(new Date());
            config.setSerialLength(dto.getSerialLength());
            mapper.updateById(config);
        } finally {
            // 删除关联redis 缓存
            relationServiceI.cleanCache(config.getRuleCode());
        }
        return RestResult.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResult<?> delete(BambooLeafConfigSaveDTO dto) {
        BambooLeafConfig config = mapper.selectById(dto.getId());
        ParamCheck.notNull(config, BambooLeafResultCode.CONFIG_IS_NOT_EXIST);
        // 验证是否被关联，被关联则被不可被删除
        List<BizLeafRelation> bizLeafRelations = relationServiceI.selectListByRuleCode(config.getRuleCode());
        ParamCheck.isEmpty(bizLeafRelations, BambooLeafResultCode.RULE_CONFIG_IS_USED);
        try {
            mapper.deleteById(dto.getId(), new Date());
        } finally {
            // 删除关联配置
            relationServiceI.cleanCache(config.getRuleCode());
        }
        return RestResult.success();
    }

    private String format(List<BambooLeafConfigFormatDTO> list) {
        List<BambooLeafConfigFormatDTO> collect = list
                .stream()
                .sorted(Comparator.comparingLong(BambooLeafConfigFormatDTO::getSort))
                .collect(Collectors.toList());
        StringBuilder stringBuffer = null;
        for (BambooLeafConfigFormatDTO b : collect) {
            if (StringTools.isEmpty(b.getFormat()) || StringTools.isEmpty(b.getNumberRuleType())) {
                throw new CustomException(ResultErrCodeI.NOT_EMPTY_CODE, "[formats]参数结构异常");
            }
            if (stringBuffer == null) {
                stringBuffer = new StringBuilder();
            } else {
                stringBuffer.append(BambooLeafConstants.FORMAT_RULE_APPEND_CHAR);
            }
            stringBuffer.append(b.getNumberRuleType());
            stringBuffer.append(BambooLeafConstants.FORMAT_STATS_APPEND_CHAR);
            stringBuffer.append(b.getFormat());
        }
        assert stringBuffer != null;
        return stringBuffer.toString();
    }

    private List<BambooLeafConfigFormatDTO> formats(String format) {
        List<BambooLeafConfigFormatDTO> list = new ArrayList<>();
        String[] splits = format.split(BambooLeafConstants.FORMAT_RULE_SPLIT_CHAR);
        long sort = 0;
        for (String split : splits) {
            BambooLeafConfigFormatDTO dto = new BambooLeafConfigFormatDTO();
            String[] split1 = split.split(BambooLeafConstants.FORMAT_STATS_APPEND_CHAR);
            dto.setNumberRuleType(split1[0]);
            dto.setFormat(split1[1]);
            dto.setSort(sort);
            list.add(dto);
            sort++;
        }
        return list;
    }
}
