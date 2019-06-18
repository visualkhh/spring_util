package com.lgu.cbc.monitor;

import com.lgu.cbc.core.CbcApplicationBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class MonitorApplication extends CbcApplicationBase {
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(MonitorApplication.class);
        builder.build().addListeners(new ApplicationPidFileWriter());
        builder.run(args);
        builder.context().getBean(MonitorApplication.class).start();
    }

    @Override
    public void loopRun() {
        log.info("MonitorApplication");
    }
}
