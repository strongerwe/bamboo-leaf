package cn.stronger.we.leaf.config;


import cn.stronger.we.leaf.constants.BambooLeafConstants;
import cn.stronger.we.leaf.constants.NumberRuleEnums;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class NumberTools.class
 * @department Platform R&D
 * @date 2025/2/18
 * @desc 数值工具类
 */
public class NumberTools {

    /**
     * 格式化样式
     *
     * @param format      format
     * @param startNumber startNumber
     * @return {@link String }
     */
    public static String formatStyle(String format, Long startNumber) {
        String[] splits = format.split(BambooLeafConstants.FORMAT_RULE_SPLIT_CHAR);
        StringBuilder stringBuffer = new StringBuilder();
        for (String split : splits) {
            String[] split1 = split.split(BambooLeafConstants.FORMAT_STATS_APPEND_CHAR);
            if (NumberRuleEnums.INCR.getType().equals(split1[0])) {
                stringBuffer.append(NumberTools.complementZero(Integer.parseInt(split1[1]), startNumber));
            } else {
                stringBuffer.append(split1[1]);
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 补零
     *
     * @param formatI formatI
     * @param number  number
     * @return {@link String }
     */
    public static String complementZero(int formatI, Long number) {
        return String.format("%0" + formatI + "d", number);
    }
}
