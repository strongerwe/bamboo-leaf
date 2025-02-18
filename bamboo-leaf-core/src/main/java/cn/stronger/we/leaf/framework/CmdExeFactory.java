package cn.stronger.we.leaf.framework;

import cn.stronger.we.commons.framework.Exe;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @class CmdExeFactory
 * @department Platform Center
 * @date 2023-08-12 15:19
 */
@Component
public class CmdExeFactory {

    private final String EXE_ENDING = "CmdExe";

    @Resource
    private ApplicationContext ioc;

    /**
     * 获取适配器
     *
     * @param name adapterName
     * @return Adapter
     */
    public Exe get(String name) {
        if (ioc.containsBean(name + EXE_ENDING)) {
            return (Exe) ioc.getBean(name + EXE_ENDING);
        }
        return null;
    }
}
