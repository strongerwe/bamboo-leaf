package cn.stronger.we.leaf.rest.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @class BambooLeafConfigTableVO
 * @department Platform Center
 * @date 2023-08-16 13:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BambooLeafConfigTableVO implements Serializable {

    private Long id;

    /**
     * 编码类型
     */
    private String ruleName;

    private String ruleCode;

    /**
     * 编码类型名称
     */
    private String resetRule;
    /**
     * 流水号位数
     */
    private Integer serialLength;
    /**
     * 起始流水号
     */
    private Long startNumber;
    /**
     * 递增规则
     */
    private Long incrRule;
    /**
     * 配置格式
     */
    private String formatStyle;

    /**
     * 创建时间
     */
    private Date createTime;

}
