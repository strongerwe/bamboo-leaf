package cn.stronger.we.leaf.rest.dto;

import cn.stronger.we.commons.framework.request.AbstractAdminPageRequest;
import cn.stronger.we.commons.framework.response.AdminPageResponse;
import cn.stronger.we.leaf.rest.vo.BambooLeafConfigTableVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @class BambooLeafConfigQuery
 * @department Platform Center
 * @date 2023-08-16 13:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BambooLeafConfigQuery extends AbstractAdminPageRequest<BambooLeafConfigTableVO> {
    /**
     * 业务编码
     */
    private String ruleName;
    /**
     * 配置类别
     */
    private Integer resetRule;
}
