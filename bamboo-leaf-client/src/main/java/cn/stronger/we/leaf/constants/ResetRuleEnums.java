package cn.stronger.we.leaf.constants;

import cn.stronger.we.commons.utils.DateTimeTools;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @enum ResetRuleEnums
 * @department Platform Center
 * @date 2023-08-12 11:32
 */
@Getter
public enum ResetRuleEnums {
    /**
     * 清零规则：序列化递增重置规则
     */
    NEVER(0, "0", "从不重置", "none", -1L),
    PER_DIEM(1, "1", "按天重置", "yyyyMMdd", 86400L),
    PER_MENSEM(2, "2", "按月重置", "yyyyMM", 2678400L),
    PER_ANNUM(3, "3", "按年重置", "yyyy", 31622400L);

    private final Integer typeI;
    private final String value;
    private final String label;
    private final String format;
    private final Long timeOut;

    ResetRuleEnums(Integer typeI, String value, String label, String format, Long timeOut) {
        this.typeI = typeI;
        this.value = value;
        this.label = label;
        this.format = format;
        this.timeOut = timeOut;
    }


    private final static Map<Integer, ResetRuleEnums> MAP = new HashMap<>();

    static {
        for (ResetRuleEnums ruleEnum : ResetRuleEnums.values()) {
            MAP.put(ruleEnum.typeI, ruleEnum);
        }
    }

    public static ResetRuleEnums getByTypeI(Integer typeI) {
        return MAP.get(typeI);
    }

    public static String formatKey(Integer typeI) {
        ResetRuleEnums resetRuleEnums = MAP.get(typeI);
        if (NEVER.getFormat().equals(resetRuleEnums.getFormat())) {
            return NEVER.getFormat();
        }
        return DateTimeTools.getFormatCurrentDate(resetRuleEnums.getFormat());
    }

}
