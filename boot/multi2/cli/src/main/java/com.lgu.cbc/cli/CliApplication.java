package com.lgu.cbc.cli;

import com.lgu.cbc.core.CbcApplicationBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
public class CliApplication extends CbcApplicationBase {
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(CliApplication.class);
        builder.build().addListeners(new ApplicationPidFileWriter());
        builder.run(args);
        builder.context().getBean(CliApplication.class).start();
    }

    @Override
    public void loopRun() {
        log.info("CliApplication");
    }
}
