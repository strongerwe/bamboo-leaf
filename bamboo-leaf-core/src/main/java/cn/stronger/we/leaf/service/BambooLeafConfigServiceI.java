package cn.stronger.we.leaf.service;

import cn.stronger.we.commons.framework.RestResult;
import cn.stronger.we.leaf.rest.dto.BambooLeafConfigQuery;
import cn.stronger.we.leaf.rest.dto.BambooLeafConfigSaveDTO;
import cn.stronger.we.leaf.rest.vo.BambooLeafConfigDetailsVO;
import cn.stronger.we.leaf.rest.vo.BambooLeafConfigTableVO;
import com.github.pagehelper.PageInfo;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @interface BambooLeafConfigServiceI
 * @department Platform Center
 * @date 2023-08-12 15:29
 */
public interface BambooLeafConfigServiceI {

    RestResult<PageInfo<BambooLeafConfigTableVO>> page(BambooLeafConfigQuery query);

    RestResult<BambooLeafConfigDetailsVO> details(String ruleCode);

    RestResult<?> add(BambooLeafConfigSaveDTO dto);

    RestResult<?> update(BambooLeafConfigSaveDTO dto);

    RestResult<?> delete(BambooLeafConfigSaveDTO dto);
}
