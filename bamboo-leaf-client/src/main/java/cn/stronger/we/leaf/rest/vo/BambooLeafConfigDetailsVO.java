package cn.stronger.we.leaf.rest.vo;

import cn.stronger.we.leaf.rest.dto.BambooLeafConfigFormatDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @class BambooLeafConfigDetailsVO
 * @department Platform Center
 * @date 2023-08-16 15:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BambooLeafConfigDetailsVO implements Serializable {

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
     * 格式规则
     */
    private List<BambooLeafConfigFormatDTO> formats;

    /**
     * 配置格式
     */
    private String formatStyle;
    /**
     * 备注说明
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

}
