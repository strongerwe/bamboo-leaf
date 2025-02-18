package cn.stronger.we.leaf.framework;

import cn.stronger.we.commons.exception.CustomException;
import cn.stronger.we.leaf.framework.dto.BizObject;

import java.util.Objects;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description 上下文
 * @class LeafContext
 * @department Platform Center
 * @date 2023-08-12 12:51
 */
public class LeafContext {
    /**
     * 输入数据结构
     */
    public static final ThreadLocal<Object> INPUT_DATA_STRUCTURE = new ThreadLocal<>();

    public static final ThreadLocal<String> OUT_DATA_STRUCTURE = new ThreadLocal<>();


    public static String getNewNumber() {
        String s = OUT_DATA_STRUCTURE.get();
        return s == null ? "" : s;
    }

    public static void appendNewNumber(String append) {
        String cat = getNewNumber();
        cat = cat + append;
        OUT_DATA_STRUCTURE.set(cat);
    }

    /**
     * 获取输入
     *
     * @return 被输入参数
     */
    public static <INPUT> INPUT getInput(Class<INPUT> clazz) {
        if (Objects.nonNull(clazz) && Objects.nonNull(INPUT_DATA_STRUCTURE.get())) {
            Object object = INPUT_DATA_STRUCTURE.get();
            if (object.getClass().isAssignableFrom(clazz)) {
                return (INPUT) object;
            }
        }
        throw new CustomException("输出对象为空或上下文获取类型参数有误");
    }

    /**
     * 获取输入
     *
     * @param type 类型
     * @return {@link BizObject}<{@link TYPE}>
     */
    public static <TYPE> BizObject<TYPE> getInput(BizObject<TYPE> type) {
        Object object = INPUT_DATA_STRUCTURE.get();
        return (BizObject<TYPE>) object;
    }

    /**
     * 设置入参
     *
     * @param input   输入
     * @param <INPUT> 自定义入参结构
     */
    public static <INPUT> void setInput(INPUT input) {
        INPUT_DATA_STRUCTURE.set(input);
    }

    /**
     * 清空输入参数
     */
    public static void clearInput() {
        INPUT_DATA_STRUCTURE.remove();
    }

    /**
     * 销毁上下文
     * 一般在拦截器或者AOP中处理
     * 正常使用请操作单挑clear操作
     */
    public static void destroy() {
        INPUT_DATA_STRUCTURE.remove();
        OUT_DATA_STRUCTURE.remove();
    }
}
