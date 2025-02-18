package cn.stronger.we.leaf.controller;

import cn.stronger.we.commons.framework.RestResult;
import cn.stronger.we.commons.validator.ParamCheck;
import cn.stronger.we.leaf.client.interfaces.BambooLeafApiI;
import cn.stronger.we.leaf.constants.BambooLeafResultCode;
import cn.stronger.we.leaf.service.BambooLeafServiceI;
import cn.stronger.we.logs.MethodCustomLog;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @class BambooLeafApiController
 * @department 平台研发部
 * @date 2023-08-14 16:31
 */
@RestController
public class BambooLeafApiController implements BambooLeafApiI {

    @Resource
    private BambooLeafServiceI bambooLeafServiceI;

    @Override
    @MethodCustomLog(msg = "生成编码")
    public RestResult<String> newId(String bizCode) {
        ParamCheck.notEmpty(bizCode, BambooLeafResultCode.BIZ_CODE_NOT_EMPTY);
        return RestResult.success(bambooLeafServiceI.newId(bizCode));
    }

    @Override
    public RestResult<String> getNewId(String bizCode) {
        return newId(bizCode);
    }
}
