package cn.stronger.we.leaf.rest.dto;

import cn.stronger.we.commons.validator.CusNotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @class BambooLeafConfigSaveDTO
 * @department Platform Center
 * @date 2023-08-16 13:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BambooLeafConfigSaveDTO implements Serializable {

    private Long id;

    @CusNotEmpty(message = "ruleName")
    private String ruleName;

    @CusNotEmpty(message = "resetRule")
    private Integer resetRule;

    @CusNotEmpty(message = "serialLength")
    private Integer serialLength;

    @CusNotEmpty(message = "startNumber")
    private Long startNumber;

    @CusNotEmpty(message = "incrRule")
    private Long incrRule;

    @CusNotEmpty(message = "formats")
    private List<BambooLeafConfigFormatDTO> formats;

    private String remark;
}
