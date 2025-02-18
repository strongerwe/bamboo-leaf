package cn.stronger.we.leaf.framework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @class LeafAllocCreateDTO
 * @department Platform Center
 * @date 2023-08-16 9:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LeafAllocCreateDTO implements Serializable {
    private String key;
    private Long maxId;
    private String dateString;
    private Long incr;
    private Long step;
    private String description;

}
