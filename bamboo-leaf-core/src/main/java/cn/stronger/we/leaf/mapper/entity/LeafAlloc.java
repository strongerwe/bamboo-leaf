package cn.stronger.we.leaf.mapper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @class LeafAlloc
 * @department Platform Center
 * @date 2023-08-15 16:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LeafAlloc implements Serializable {
    private String key;
    private long maxId;
    private long incr;
    private long step;
    private String dateStr;
    private String description;
    private Date updateTime;
    private Boolean isUsed;
}
