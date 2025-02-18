package cn.stronger.we.leaf.framework.segment;

import cn.stronger.we.leaf.framework.dto.LeafAllocCreateDTO;
import cn.stronger.we.leaf.mapper.entity.LeafAlloc;

import java.util.List;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @interface IDAllocDao
 * @department Platform Center
 * @date 2023-08-15 16:53
 */
public interface IDAllocDao {
    List<LeafAlloc> getAllLeafAllocs();
    LeafAlloc updateMaxIdAndGetLeafAlloc(String tag);
    LeafAlloc updateMaxIdByCustomStepAndGetLeafAlloc(LeafAlloc leafAlloc);
    List<String> getAllTags();
    LeafAlloc getByKeyAndCreate(LeafAllocCreateDTO dto);
}
