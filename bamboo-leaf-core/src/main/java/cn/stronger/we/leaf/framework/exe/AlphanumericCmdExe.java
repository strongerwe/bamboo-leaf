package cn.stronger.we.leaf.framework.exe;

import cn.stronger.we.commons.framework.Exe;
import cn.stronger.we.leaf.framework.LeafContext;
import cn.stronger.we.leaf.framework.dto.NumberRuleDTO;
import org.springframework.stereotype.Component;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description 字符串拼接
 * @class AlphanumericCmdExe
 * @department Platform Center
 * @date 2023-08-12 12:10
 */
@Component(value = "AlphanumericCmdExe")
public class AlphanumericCmdExe implements Exe<NumberRuleDTO, Void> {

    @Override
    public Void execute(NumberRuleDTO numberRuleDTO) {
        LeafContext.appendNewNumber(numberRuleDTO.getFormat());
        return null;
    }

    @Override
    public void validate(NumberRuleDTO numberRuleDTO) {

    }
}
