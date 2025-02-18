package cn.stronger.we;

import cn.stronger.we.commons.framework.RestResult;
import cn.stronger.we.commons.utils.JsonTools;
import cn.stronger.we.leaf.RunApplication;
import cn.stronger.we.leaf.controller.BambooLeafApiController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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

    // 添加一个静态变量来跟踪线程ID
    private static final AtomicInteger threadIdCounter = new AtomicInteger(0);

    @Test
    public void newId() {
        RestResult<String> stringRestResult = controller.newId("payment-center");
        System.out.println(JsonTools.toJson(stringRestResult));
    }

    @Test
    public void testNewIdMultithreaded() throws InterruptedException {
        int numberOfThreads = 20; // 设置并发线程数
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        Random random = new Random(); // 创建一个随机数生成器

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    long time = random.nextInt(1000) + 1;
                    Thread.sleep(time); // 随机睡眠1到1000毫秒
                    // 获取并递增自定义线程ID
                    int customThreadId = threadIdCounter.incrementAndGet();
                    RestResult<String> stringRestResult = controller.newId("payment-center");
                    System.out.println("Custom Thread ID: " + customThreadId + " Generated ID|" + time + "ms: " + stringRestResult.getData());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES); // 等待所有线程完成

    }

}