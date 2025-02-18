package cn.stronger.we.leaf;


import cn.stronger.we.leaf.framework.segment.SegmentIDGenImpl;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class RunApplication.class
 * @department Platform R&D
 * @date 2025/2/18
 * @desc 启动类
 */
@Slf4j
@MapperScan("cn.stronger.we.leaf.mapper")
@SpringBootApplication
public class RunApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(RunApplication.class, args);
        String application = run.getEnvironment().getProperty("spring.application.name");
        String port = run.getEnvironment().getProperty("server.port");
        SegmentIDGenImpl bean = run.getBean(SegmentIDGenImpl.class);
        bean.init();
        log.info("{} [{}] RUNNING SUCCESS! >>>>>>", application, port);
    }
}
