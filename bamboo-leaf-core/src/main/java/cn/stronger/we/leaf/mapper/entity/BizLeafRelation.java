package cn.stronger.we.leaf.mapper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @class BizLeafRelation
 * @department Platform Center
 * @date 2023-08-24 15:08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("biz_leaf_relation")
@AllArgsConstructor
@NoArgsConstructor
public class BizLeafRelation implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String bizCode;

    private String ruleCode;
}
