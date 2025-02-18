package cn.stronger.we.leaf.rest.dto;

import cn.stronger.we.commons.validator.CusNotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @class BizLeafRelationSaveDTO
 * @department Platform Center
 * @date 2023-08-24 15:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BizLeafRelationSaveDTO implements Serializable {

    @CusNotEmpty(message = "bizCode")
    private String bizCode;

    @CusNotEmpty(message = "ruleCode")
    private String ruleCode;
}
