package cn.stronger.we.leaf.client.interfaces;

import cn.stronger.we.commons.framework.RestResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @interface BambooLeafApiI
 * @department Platform Center
 * @date 2023-08-14 16:29
 */
public interface BambooLeafApiI {

    /**
     * 生产新ID
     *
     * @param bizCode bizCode
     * @return String
     */
    @GetMapping("/bamboo/leaf/get/{key}")
    RestResult<String> newId(@PathVariable("key") String bizCode);


    /**
     * 创建新编码
     *
     * @param bizCode bizCode
     * @return {@link RestResult }<{@link String }>
     */
    @GetMapping("/bamboo/leaf/newId")
    RestResult<String> getNewId(@RequestParam(value = "bizCode") String bizCode);
}
