package cn.stronger.we.leaf.client;

import cn.stronger.we.leaf.client.interfaces.BizLeafRelationApiI;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @class BizLeafRelationApiClient
 * @department Platform Center
 * @date 2023-08-24 16:10
 */
@FeignClient(name = "${service.leaf.name:bamboo-leaf}",
        url = "${service.leaf.url:http://bamboo-leaf.pro}",
        contextId = "bizLeafRelationApiClient")
public interface BizLeafRelationApiClient extends BizLeafRelationApiI {
}
