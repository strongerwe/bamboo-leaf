package cn.stronger.we.leaf.rest.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @class BizLeafRelationTableVO
 * @department Platform Center
 * @date 2023-08-24 15:39
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BizLeafRelationTableVO extends BambooLeafConfigTableVO {

    private String bizCode;
}
