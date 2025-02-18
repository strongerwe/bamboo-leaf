package cn.stronger.we.leaf.controller;

import cn.stronger.we.commons.framework.RestResult;
import cn.stronger.we.commons.framework.ResultErrCodeI;
import cn.stronger.we.commons.validator.ParamCheck;
import cn.stronger.we.leaf.client.interfaces.BambooLeafConfigApiI;
import cn.stronger.we.leaf.rest.dto.BambooLeafConfigQuery;
import cn.stronger.we.leaf.rest.dto.BambooLeafConfigSaveDTO;
import cn.stronger.we.leaf.rest.vo.BambooLeafConfigDetailsVO;
import cn.stronger.we.leaf.rest.vo.BambooLeafConfigTableVO;
import cn.stronger.we.leaf.service.BambooLeafConfigServiceI;
import cn.stronger.we.logs.MethodCustomLog;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.MessageFormat;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @class BambooLeafConfigApiController
 * @department 平台研发部
 * @date 2023-08-16 14:46
 */
@RestController
public class BambooLeafConfigApiController implements BambooLeafConfigApiI {

    @Resource
    private BambooLeafConfigServiceI configServiceI;

    @Override
    @MethodCustomLog(msg = "配置列表查询")
    public RestResult<PageInfo<BambooLeafConfigTableVO>> page(BambooLeafConfigQuery query) {
        return configServiceI.page(query);
    }

    @Override
    @MethodCustomLog(msg = "配置详情查询")
    public RestResult<BambooLeafConfigDetailsVO> details(String ruleCode) {
        return configServiceI.details(ruleCode);
    }

    @Override
    @MethodCustomLog(msg = "配置新增")
    public RestResult<?> add(BambooLeafConfigSaveDTO dto) {
        return configServiceI.add(dto);
    }

    @Override
    @MethodCustomLog(msg = "配置编辑")
    public RestResult<?> update(BambooLeafConfigSaveDTO dto) {
        ParamCheck.notNull(dto.getId(),
                ResultErrCodeI.NOT_EMPTY_CODE,
                MessageFormat.format(ResultErrCodeI.NOT_EMPTY_MSG, "Id"));
        return configServiceI.update(dto);
    }

    @Override
    @MethodCustomLog(msg = "配置删除")
    public RestResult<?> delete(BambooLeafConfigSaveDTO dto) {
        ParamCheck.notNull(dto.getId(),
                ResultErrCodeI.NOT_EMPTY_CODE,
                MessageFormat.format(ResultErrCodeI.NOT_EMPTY_MSG, "Id"));
        return configServiceI.delete(dto);
    }
}
