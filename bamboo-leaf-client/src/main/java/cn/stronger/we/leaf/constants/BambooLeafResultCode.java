package cn.stronger.we.leaf.constants;

import cn.stronger.we.commons.framework.ResultErrCodeI;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @enum BambooLeafResultCode
 * @department Platform Center
 * @date 2023-08-12 16:26
 */
public enum BambooLeafResultCode implements ResultErrCodeI {
    /**
     *
     */
    BIZ_CODE_NOT_EMPTY("LEAF-99000", "bizCode不能为空"),
    CONFIG_IS_NOT_EXIST("LEAF-99001", "编号配置规则不存在"),
    SEGMENT_ERROR("LEAF-99002", "创建流水号失败"),
    CONFIG_IS_EXIST("LEAF-99003", "编号配置规则已存在"),
    BIZ_CONFIG_IS_EXIST("LEAF-99004","业务编号规则已存在"),
    BIZ_CONFIG_IS_NOT_EXIST("LEAF-99005","业务编号规则不存在"),
    RULE_CONFIG_IS_USED("LEAF-99006","规则配置已被业务使用，不可删除！")
    ;

    private final String code;
    private final String message;

    BambooLeafResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

    public String getJson() {
        return "{\"code\": \"" + code() + "\",\"message\": \"" + message() + "\",\"responseType\": \"error\",\"succeed\": false}";
    }
}
