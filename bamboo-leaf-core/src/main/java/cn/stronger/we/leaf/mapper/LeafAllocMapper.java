package cn.stronger.we.leaf.mapper;

import cn.stronger.we.leaf.mapper.entity.LeafAlloc;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @interface LeafAllocMapper
 * @department Platform Center
 * @date 2023-08-15 16:44
 */
public interface LeafAllocMapper {
    /**
     * 获取所有LeafAlloc
     *
     * @return {@link List }<{@link LeafAlloc }>
     */
    List<LeafAlloc> getAllLeafAllocs();

    /**
     * 获取LeafAlloc
     *
     * @param tag tag
     * @return {@link LeafAlloc }
     */
    LeafAlloc getLeafAlloc(@Param("tag") String tag);

    /**
     * 更新
     *
     * @param tag tag
     */
    void updateMaxId(@Param("tag") String tag);

    /**
     * 更新
     *
     * @param leafAlloc leafAlloc
     */
    void updateMaxIdByCustomStep(@Param("leafAlloc") LeafAlloc leafAlloc);

    /**
     * 更新Used
     *
     * @param dateStr dateStr
     */
    void updateUsed(@Param("dateStr") String dateStr);

    /**
     * 获取所有标签
     *
     * @return {@link List }<{@link String }>
     */
    List<String> getAllTags();

    /**
     * 插入
     *
     * @param leafAlloc leafAlloc
     */
    void insert(@Param("leafAlloc") LeafAlloc leafAlloc);
}
