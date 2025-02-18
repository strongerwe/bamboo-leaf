package cn.stronger.we.leaf.service;

import cn.stronger.we.leaf.framework.LeafCore;
import cn.stronger.we.leaf.framework.dto.BambooLeafConfigDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @class BambooLeafServiceImpl
 * @department Platform Center
 * @date 2023-08-14 16:42
 */
@Service
public class BambooLeafServiceImpl implements BambooLeafServiceI {
    @Resource
    private BizLeafRelationServiceI relationServiceI;

    @Resource
    private LeafCore core;

    @Override
    public String newId(String bizCode) {
        BambooLeafConfigDTO byCode = relationServiceI.getByBizCode(bizCode);
        return core.newId(byCode);
    }
}
