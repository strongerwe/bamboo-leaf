package cn.stronger.we.leaf.client.interfaces;

import cn.stronger.we.commons.framework.RestResult;
import cn.stronger.we.leaf.rest.dto.BizLeafRelationQuery;
import cn.stronger.we.leaf.rest.dto.BizLeafRelationSaveDTO;
import cn.stronger.we.leaf.rest.vo.BambooLeafConfigTableVO;
import cn.stronger.we.leaf.rest.vo.BizLeafRelationTableVO;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @interface BizLeafRelationApiI
 * @department Platform Center
 * @date 2023-08-24 15:41
 */
public interface BizLeafRelationApiI {

    /**
     * 列表分页查询
     *
     * @param query query
     * @return PageInfo
     */
    @GetMapping("/bamboo/leaf/relation/page.do")
    RestResult<PageInfo<BizLeafRelationTableVO>> page(@SpringQueryMap BizLeafRelationQuery query);

    /**
     * 获取ID规则列表
     *
     * @return RestResult
     */
    @GetMapping("/bamboo/leaf/relation/listAll.do")
    RestResult<?> listAll();

    @GetMapping("/bamboo/leaf/relation/ruleSelected.do")
    RestResult<List<BambooLeafConfigTableVO>> ruleSelected();

    /**
     * 新增配置
     *
     * @param dto dto
     * @return {@link RestResult }<{@link ? }>
     */
    @PostMapping("/bamboo/leaf/relation/add.do")
    RestResult<?> add(@RequestBody @Validated BizLeafRelationSaveDTO dto);


    /**
     * 修改配置
     *
     * @param dto dto
     * @return {@link RestResult }<{@link ? }>
     */
    @PutMapping("/bamboo/leaf/relation/update.do")
    RestResult<?> update(@RequestBody @Validated BizLeafRelationSaveDTO dto);

    /**
     * 删除配置
     *
     * @param dto dto
     * @return {@link RestResult }<{@link ? }>
     */
    @PutMapping("/bamboo/leaf/relation/delete.do")
    RestResult<?> delete(@RequestBody BizLeafRelationSaveDTO dto);
}
