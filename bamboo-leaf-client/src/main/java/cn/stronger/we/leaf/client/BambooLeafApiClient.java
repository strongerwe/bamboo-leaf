package cn.stronger.we.leaf.client;

import cn.stronger.we.leaf.client.interfaces.BambooLeafApiI;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description BambooLeafApiClient
 * @interface BambooLeafApiClient
 * @department Platform Center
 * @date 2023-08-15 9:21
 */
@FeignClient(name = "${service.leaf.name:bamboo-leaf}",
        url = "${service.leaf.url:http://bamboo-leaf.pro}",
        contextId = "bambooLeafApiClient")
public interface BambooLeafApiClient extends BambooLeafApiI {

}
