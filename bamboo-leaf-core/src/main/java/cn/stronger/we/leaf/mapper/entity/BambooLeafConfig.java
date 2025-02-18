package cn.stronger.we.leaf.mapper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @class BambooLeafConfig
 * @department Platform Center
 * @date 2023-08-12 10:48
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("bamboo_leaf_config")
@AllArgsConstructor
@NoArgsConstructor
public class BambooLeafConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * show|主键ID||
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String ruleName;

    private String ruleCode;

    private Integer resetRule;

    private Integer serialLength;

    private Long startNumber;

    private Long incrRule;

    private String format;

    private String remark;

    private Date createTime;

    private Date updateTime;

    /**
     * show|删除标识|pf_is_deleted|0.正常；1.已删除
     */
    @TableLogic
    private Boolean isDeleted;
}
