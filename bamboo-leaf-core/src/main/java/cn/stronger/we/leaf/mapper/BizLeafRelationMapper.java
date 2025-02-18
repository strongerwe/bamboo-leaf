package cn.stronger.we.leaf.mapper;

import cn.stronger.we.leaf.framework.dto.BambooLeafConfigDTO;
import cn.stronger.we.leaf.mapper.entity.BizLeafRelation;
import cn.stronger.we.leaf.rest.dto.BizLeafRelationQuery;
import cn.stronger.we.leaf.rest.vo.BizLeafRelationTableVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @interface BizLeafRelationMapper
 * @department Platform Center
 * @date 2023-08-24 15:10
 */
public interface BizLeafRelationMapper {

    /**
     * 列表查询
     *
     * @param query query
     * @return {@link List }<{@link BizLeafRelationTableVO }>
     */
    List<BizLeafRelationTableVO> list(@Param("query") BizLeafRelationQuery query);

    List<String> listAll();

    /**
     * 根据bizCode查询
     *
     * @param bizCode bizCode
     * @return BizLeafRelation
     */
    BizLeafRelation selectByBizCode(@Param("bizCode") String bizCode);

    /**
     * 根据ruleCode查询关联配置
     *
     * @param ruleCode ruleCode
     * @return {@link List }<{@link BizLeafRelation }>
     */
    List<BizLeafRelation> selectByRuleCode(@Param("ruleCode") String ruleCode);

    /**
     * 根据bizCode获取配置
     *
     * @param bizCdoe bizCdoe
     * @return {@link BambooLeafConfigDTO }
     */
    BambooLeafConfigDTO selectConfigByBizCode(@Param("bizCode") String bizCdoe);

    /**
     * 新增
     *
     * @param relation relation
     */
    void insert(@Param("relation") BizLeafRelation relation);

    /**
     * 修改
     *
     * @param relation relation
     */
    void updateById(@Param("relation") BizLeafRelation relation);

    /**
     * 删除
     *
     * @param relation relation
     */
    void deleteById(@Param("relation") BizLeafRelation relation);
}
