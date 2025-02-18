package cn.stronger.we.leaf.framework.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @class BizObject
 * @department Platform Center
 * @date 2023-08-12 12:48
 */
@Data
@Accessors(chain = true)
public class BizObject<DATA> implements Serializable {
    private DATA data;
    private String bizCode;
    private String newNumber;
}
