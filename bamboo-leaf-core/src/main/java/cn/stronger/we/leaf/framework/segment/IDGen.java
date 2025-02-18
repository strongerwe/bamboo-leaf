package cn.stronger.we.leaf.framework.segment;

import cn.stronger.we.leaf.framework.dto.LeafAllocCreateDTO;
import cn.stronger.we.leaf.mapper.entity.LeafAlloc;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @interface IDGen
 * @department Platform Center
 * @date 2023-08-15 16:50
 */
public interface IDGen {
    Result get(String key);
    boolean init();
    LeafAlloc getByKey(LeafAllocCreateDTO dto);
}
