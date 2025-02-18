package cn.stronger.we.leaf.controller;

import cn.stronger.we.commons.framework.RestResult;
import cn.stronger.we.leaf.client.interfaces.BizLeafRelationApiI;
import cn.stronger.we.leaf.rest.dto.BizLeafRelationQuery;
import cn.stronger.we.leaf.rest.dto.BizLeafRelationSaveDTO;
import cn.stronger.we.leaf.rest.vo.BambooLeafConfigTableVO;
import cn.stronger.we.leaf.rest.vo.BizLeafRelationTableVO;
import cn.stronger.we.leaf.service.BizLeafRelationServiceI;
import cn.stronger.we.logs.MethodCustomLog;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @class BizLeafRelationApiController
 * @department 平台研发部
 * @date 2023-08-24 16:14
 */
@RestController
public class BizLeafRelationApiController implements BizLeafRelationApiI {

    @Resource
    BizLeafRelationServiceI bizLeafRelationServiceI;

    @Override
    @MethodCustomLog(msg = "分页查询关联")
    public RestResult<PageInfo<BizLeafRelationTableVO>> page(BizLeafRelationQuery query) {
        return bizLeafRelationServiceI.page(query);
    }

    @Override
    @MethodCustomLog(msg = "获取已配置业务编码")
    public RestResult<?> listAll() {
        return bizLeafRelationServiceI.listAll();
    }

    @Override
    public RestResult<List<BambooLeafConfigTableVO>> ruleSelected() {
        return bizLeafRelationServiceI.ruleSelected();
    }

    @Override
    @MethodCustomLog(msg = "添加关联")
    public RestResult<?> add(BizLeafRelationSaveDTO dto) {
        return bizLeafRelationServiceI.add(dto);
    }

    @Override
    @MethodCustomLog(msg = "更新关联")
    public RestResult<?> update(BizLeafRelationSaveDTO dto) {
        return bizLeafRelationServiceI.update(dto);
    }

    @Override
    @MethodCustomLog(msg = "删除关联")
    public RestResult<?> delete(BizLeafRelationSaveDTO dto) {
        return bizLeafRelationServiceI.delete(dto);
    }
}
