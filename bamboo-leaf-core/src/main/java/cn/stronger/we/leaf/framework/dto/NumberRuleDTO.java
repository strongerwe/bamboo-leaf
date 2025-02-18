package cn.stronger.we.leaf.framework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description 流水号凭借规则：
 * 0,SMS|1,6,1,1|2,YY
 * @class NumberRuleDTO
 * @department Platform Center
 * @date 2023-08-12 11:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class NumberRuleDTO implements Serializable {
    /**
     * 流水号规则类型
     */
    private String numberRuleType;
    /**
     * INCR("1", "递增流水号"), : 递增流水号补齐位数
     * ALPHANUMERIC("0", "字母数字替换"), : 拼接字符串
     * DATE_FORMAT("2", "日期格式替换"); : 日期转换格式
     */
    private String format;

    public NumberRuleDTO(String rule){
        String[] split = rule.split(",");
        this.numberRuleType = split[0];
        this.format = split[1];
    }
}
