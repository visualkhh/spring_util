package com.lgu.cbc.tlo;

import com.lgu.cbc.core.CbcApplicationBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication @Slf4j
public class TloApplication extends CbcApplicationBase {
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(TloApplication.class);
        builder.build().addListeners(new ApplicationPidFileWriter());
        builder.run(args);
        builder.context().getBean(TloApplication.class).start();
    }

    @Override
    public void loopRun() {
        log.info("TloApplication");
    }
}
