package cn.stronger.we.leaf.framework.segment;

import cn.stronger.we.leaf.framework.dto.LeafAllocCreateDTO;
import cn.stronger.we.leaf.mapper.entity.LeafAlloc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @class SegmentService
 * @department Platform Center
 * @date 2023-08-15 16:16
 */
@Slf4j
@Service("SegmentService")
public class SegmentService {

    @Resource
    private IDGen idGen;

    public Result getId(String key) {
        return idGen.get(key);
    }

    public LeafAlloc getByKey(LeafAllocCreateDTO dto){
        return idGen.getByKey(dto);
    }

    public SegmentIDGenImpl getIdGen() {
        if (idGen instanceof SegmentIDGenImpl) {
            return (SegmentIDGenImpl) idGen;
        }
        return null;
    }
}
