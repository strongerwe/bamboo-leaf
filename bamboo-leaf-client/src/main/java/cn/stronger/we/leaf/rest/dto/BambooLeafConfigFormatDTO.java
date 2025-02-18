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
 * @class BambooLeafConfigFormatDTO
 * @department Platform Center
 * @date 2023-08-16 13:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BambooLeafConfigFormatDTO implements Serializable {

    /**
     * 排序号
     */
    @CusNotEmpty(message = "formats.sort")
    private Long sort;

    /**
     * 流水号规则类型
     * INCR("SegmentIncr", "1", "递增流水号"),
     * ALPHANUMERIC("Alphanumeric", "0", "字母数字替换"),
     * DATE_FORMAT("DateFormat", "2", "日期格式替换");
     */
    @CusNotEmpty(message = "formats.numberRuleType")
    private String numberRuleType;
    /**
     * INCR("1", "递增流水号"), : 递增流水号补齐位数
     * ALPHANUMERIC("0", "字母数字替换"), : 拼接字符串
     * DATE_FORMAT("2", "日期格式替换"); : 日期转换格式
     */
    @CusNotEmpty(message = "formats.format")
    private String format;
}
