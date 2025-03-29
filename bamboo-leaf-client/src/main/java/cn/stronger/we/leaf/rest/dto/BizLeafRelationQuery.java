package cn.stronger.we.leaf.rest.dto;

import cn.stronger.we.commons.framework.request.AbstractAdminPageRequest;
import cn.stronger.we.leaf.rest.vo.BizLeafRelationTableVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @class BizLeafRelationQuery
 * @department Platform Center
 * @date 2023-08-24 15:42
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BizLeafRelationQuery extends AbstractAdminPageRequest<BizLeafRelationTableVO> {

    private String bizCode;

    private String ruleCode;
}
