package cn.stronger.we;


import cn.stronger.we.commons.framework.RestResult;
import cn.stronger.we.commons.utils.JsonTools;
import cn.stronger.we.leaf.RunApplication;
import cn.stronger.we.leaf.controller.BambooLeafApiController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class LeafNewIdTest.class
 * @department Platform R&D
 * @date 2025/2/18
 * @desc do what?
 */
@SpringBootTest(classes = RunApplication.class)
public class LeafNewIdTest {

    @Resource
    private BambooLeafApiController controller;

    @Test
    public void newId() {
        RestResult<String> stringRestResult = controller.newId("payment-center");
        System.out.println(JsonTools.toJson(stringRestResult));
    }
}
