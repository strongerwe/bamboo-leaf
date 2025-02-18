package cn.stronger.we.leaf.framework.dto;

import cn.stronger.we.leaf.mapper.entity.BambooLeafConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @class BambooLeafConfigSaveDTO
 * @department Platform Center
 * @date 2023-08-12 15:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BambooLeafConfigDTO implements Serializable {

    private String bizCode;

    private String ruleCode;

    private String ruleName;

    private Integer resetRule;

    private Integer serialLength;

    private Long startNumber;

    private Long incrRule;

    private String format;

    public BambooLeafConfigDTO(BambooLeafConfig config) {
        this.ruleCode = config.getRuleCode();
        this.ruleName = config.getRuleName();
        this.resetRule = config.getResetRule();
        this.serialLength = config.getSerialLength();
        this.startNumber = config.getStartNumber();
        this.incrRule = config.getIncrRule();
        this.format = config.getFormat();
    }
}
