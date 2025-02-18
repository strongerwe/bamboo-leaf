package cn.stronger.we.leaf.framework.segment;

import cn.stronger.we.leaf.framework.dto.LeafAllocCreateDTO;
import cn.stronger.we.leaf.mapper.LeafAllocMapper;
import cn.stronger.we.leaf.mapper.entity.LeafAlloc;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @class IDAllocDaoImpl
 * @department Platform Center
 * @company 华卓科技
 * @date 2023-08-15 16:53
 */
@Component
public class IDAllocDaoImpl implements IDAllocDao {
    @Resource
    private LeafAllocMapper mapper;

    @Override
    public List<LeafAlloc> getAllLeafAllocs() {
        return mapper.getAllLeafAllocs();
    }

    @Override
    public LeafAlloc updateMaxIdAndGetLeafAlloc(String tag) {
        mapper.updateMaxId(tag);
        return mapper.getLeafAlloc(tag);
    }

    @Override
    public LeafAlloc updateMaxIdByCustomStepAndGetLeafAlloc(LeafAlloc leafAlloc) {
        mapper.updateMaxIdByCustomStep(leafAlloc);
        return mapper.getLeafAlloc(leafAlloc.getKey());
    }

    @Override
    public List<String> getAllTags() {
        return mapper.getAllTags();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LeafAlloc getByKeyAndCreate(LeafAllocCreateDTO dto) {
        LeafAlloc leafAlloc = mapper.getLeafAlloc(dto.getKey());
        if (null == leafAlloc) {
            // 不存在则新增
            leafAlloc = new LeafAlloc(dto.getKey(), dto.getMaxId(), dto.getIncr(), dto.getStep(), dto.getDateString(), dto.getDescription(), new Date(), Boolean.FALSE);
            mapper.insert(leafAlloc);
        }
        return leafAlloc;
    }
}
