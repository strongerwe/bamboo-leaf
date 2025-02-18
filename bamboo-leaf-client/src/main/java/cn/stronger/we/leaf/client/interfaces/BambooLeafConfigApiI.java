package cn.stronger.we.leaf.client.interfaces;

import cn.stronger.we.commons.framework.RestResult;
import cn.stronger.we.leaf.rest.dto.BambooLeafConfigQuery;
import cn.stronger.we.leaf.rest.dto.BambooLeafConfigSaveDTO;
import cn.stronger.we.leaf.rest.vo.BambooLeafConfigDetailsVO;
import cn.stronger.we.leaf.rest.vo.BambooLeafConfigTableVO;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @interface BambooLeafConfigApiI
 * @department Platform Center
 * @date 2023-08-15 9:18
 */
public interface BambooLeafConfigApiI {

    /**
     * 获取ID规则列表
     *
     * @param query query
     * @return RestResult
     */
    @GetMapping("/bamboo/leaf/config/page.do")
    RestResult<PageInfo<BambooLeafConfigTableVO>> page(@SpringQueryMap BambooLeafConfigQuery query);

    /**
     * 获取ID规则详情
     *
     * @param ruleCode ruleCode
     * @return RestResult
     */
    @GetMapping("/bamboo/leaf/config/details.do")
    RestResult<BambooLeafConfigDetailsVO> details(@RequestParam("ruleCode") String ruleCode);

    /**
     * 新增规则
     *
     * @param dto dto
     * @return RestResult
     */
    @PostMapping("/bamboo/leaf/config/add.do")
    RestResult<?> add(@RequestBody @Validated BambooLeafConfigSaveDTO dto);

    /**
     * 编辑规则
     *
     * @param dto dto
     * @return RestResult
     */
    @PutMapping("/bamboo/leaf/config/update.do")
    RestResult<?> update(@RequestBody @Validated BambooLeafConfigSaveDTO dto);

    /**
     * 删除规则
     *
     * @param dto dto
     * @return RestResult
     */
    @PutMapping("/bamboo/leaf/config/delete.do")
    RestResult<?> delete(@RequestBody BambooLeafConfigSaveDTO dto);
}
