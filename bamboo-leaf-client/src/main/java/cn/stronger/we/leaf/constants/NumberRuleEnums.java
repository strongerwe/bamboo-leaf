package cn.stronger.we.leaf.constants;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description 序列化规则类型
 * @enum NumberRuleEnums
 * @department Platform Center
 * @date 2023-08-12 11:42
 */
@Getter
public enum NumberRuleEnums {
    /**
     * 序列化格式规则
     */
    INCR("SegmentIncr", "1", "递增流水号"),
    ALPHANUMERIC("Alphanumeric", "0", "字母数字替换"),
    DATE_FORMAT("DateFormat", "2", "日期格式替换");

    private final String ruleTag;
    private final String type;
    private final String value;

    NumberRuleEnums(String ruleTag, String type, String value) {
        this.ruleTag = ruleTag;
        this.type = type;
        this.value = value;
    }

    private final static Map<String, NumberRuleEnums> MAP = new HashMap<>();

    static {
        for (NumberRuleEnums retrieveEnum : NumberRuleEnums.values()) {
            MAP.put(retrieveEnum.type, retrieveEnum);
        }
    }

    public static NumberRuleEnums getByType(String type) {
        return MAP.get(type);
    }
}
