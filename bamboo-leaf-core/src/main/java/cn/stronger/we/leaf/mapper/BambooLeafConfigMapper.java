package cn.stronger.we.leaf.mapper;

import cn.stronger.we.leaf.mapper.entity.BambooLeafConfig;
import cn.stronger.we.leaf.rest.dto.BambooLeafConfigQuery;
import cn.stronger.we.leaf.rest.vo.BambooLeafConfigTableVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @interface BambooLeafConfigMapper
 * @department Platform Center
 * @date 2023-08-12 11:13
 */
public interface BambooLeafConfigMapper {

    /**
     * list查询
     *
     * @param query query
     * @return list
     */
    List<BambooLeafConfigTableVO> list(@Param("query") BambooLeafConfigQuery query);

    /**
     * 根据ruleName查询
     *
     * @param ruleName ruleName
     * @return BambooLeafConfig.class
     */
    BambooLeafConfig selectByRuleName(@Param("ruleName") String ruleName);

    /**
     * 根据ruleCode查询
     *
     * @param ruleCode ruleName
     * @return BambooLeafConfig.class
     */
    BambooLeafConfig selectByRuleCode(@Param("ruleCode") String ruleCode);

    /**
     * 根据Id查询
     *
     * @param id id
     * @return {@link BambooLeafConfig }
     */
    BambooLeafConfig selectById(@Param("id") Long id);

    /**
     * 新增
     *
     * @param config config
     */
    void insert(@Param("config") BambooLeafConfig config);

    /**
     * 更新数据
     *
     * @param config config
     */
    void updateById(@Param("config") BambooLeafConfig config);

    /**
     * 根据Id删除
     *
     * @param id  id
     * @param now now
     */
    void deleteById(@Param("id") Long id, @Param("now") Date now);
}
