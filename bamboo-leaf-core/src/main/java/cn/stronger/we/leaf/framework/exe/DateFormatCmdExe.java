package cn.stronger.we.leaf.framework.exe;

import cn.stronger.we.commons.framework.Exe;
import cn.stronger.we.commons.utils.DateTimeTools;
import cn.stronger.we.leaf.framework.LeafContext;
import cn.stronger.we.leaf.framework.dto.NumberRuleDTO;
import org.springframework.stereotype.Component;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description 日期格式替换
 * @class DateFormatCmdExe
 * @department Platform Center
 * @date 2023-08-12 12:11
 */
@Component(value = "DateFormatCmdExe")
public class DateFormatCmdExe implements Exe<NumberRuleDTO, String> {

    @Override
    public String execute(NumberRuleDTO numberRuleDTO) {
        String df = DateTimeTools.getFormatCurrentDate(numberRuleDTO.getFormat());
        LeafContext.appendNewNumber(df);
        return null;
    }

    @Override
    public void validate(NumberRuleDTO numberRuleDTO) {

    }
}
