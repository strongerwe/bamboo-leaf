package cn.stronger.we.leaf.service;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @interface BambooLeafServiceI
 * @department Platform Center
 * @date 2023-08-14 16:41
 */
public interface BambooLeafServiceI {

    /**
     * 生产新ID
     *
     * @param bizCode bizCode
     * @return {@link String }
     */
    String newId(String bizCode);
}
