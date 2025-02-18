package cn.stronger.we.leaf.service;

import cn.stronger.we.commons.framework.RestResult;
import cn.stronger.we.leaf.framework.dto.BambooLeafConfigDTO;
import cn.stronger.we.leaf.mapper.entity.BizLeafRelation;
import cn.stronger.we.leaf.rest.dto.BizLeafRelationQuery;
import cn.stronger.we.leaf.rest.dto.BizLeafRelationSaveDTO;
import cn.stronger.we.leaf.rest.vo.BambooLeafConfigTableVO;
import cn.stronger.we.leaf.rest.vo.BizLeafRelationTableVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @interface BizLeafRelationServiceI
 * @department Platform Center
 * @date 2023-08-24 16:31
 */
public interface BizLeafRelationServiceI {

    BambooLeafConfigDTO getByBizCode(String bizCode);

    RestResult<PageInfo<BizLeafRelationTableVO>> page(BizLeafRelationQuery query);

    RestResult<?> listAll();

    RestResult<List<BambooLeafConfigTableVO>> ruleSelected();

    RestResult<?> add(BizLeafRelationSaveDTO dto);

    RestResult<?> update(BizLeafRelationSaveDTO dto);

    RestResult<?> delete(BizLeafRelationSaveDTO dto);

    void cleanCache(String ruleCode);

    List<BizLeafRelation> selectListByRuleCode(String ruleCode);
}
