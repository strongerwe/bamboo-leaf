package cn.stronger.we.leaf.client;

import cn.stronger.we.leaf.client.interfaces.BambooLeafConfigApiI;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @interface BambooLeafConfigApiClient
 * @department Platform Center
 * @date 2023-08-16 16:24
 */
@FeignClient(name = "${service.leaf.name:bamboo-leaf}",
        url = "${service.leaf.url:http://bamboo-leaf.pro}",
        contextId = "bambooLeafConfigApiClient")
public interface BambooLeafConfigApiClient extends BambooLeafConfigApiI {
}
