package cn.stronger.we.leaf.framework;

import cn.stronger.we.commons.framework.Exe;
import cn.stronger.we.leaf.constants.NumberRuleEnums;
import cn.stronger.we.leaf.framework.dto.BambooLeafConfigDTO;
import cn.stronger.we.leaf.framework.dto.NumberRuleDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @class LeafCore
 * @department Platform Center
 * @date 2023-08-12 11:59
 */
@Component
public class LeafCore {

    @Resource
    private CmdExeFactory factory;

    public String newId(BambooLeafConfigDTO byCode) {
        try {
            // 配置保存上下文
            LeafContext.setInput(byCode);
            String[] splits = byCode.getFormat().split("\\|");
            for (String split : splits) {
                Exe<NumberRuleDTO, String> exe = factory.get(NumberRuleEnums.getByType(split.substring(0, 1)).getRuleTag());
                exe.execute(new NumberRuleDTO(split));
            }
            return LeafContext.getNewNumber();
        } finally {
            LeafContext.destroy();
        }
    }
}
