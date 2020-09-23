package com.genome.dx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@SpringBootApplication
@EnableAsync
public class KhidApplication {


    @Value("${spring.application.name}")
    String applicationName;

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(KhidApplication.class);
        builder.build().addListeners(new ApplicationPidFileWriter());
        builder.run(args);
    }

    @EventListener
    public void applicationStartedEvent(ApplicationStartedEvent applicationStartedEvent) {
    }

    @PostConstruct
    public void onStart() {
        log.info("START {}", applicationName);
    }

    @PreDestroy
    public void onExit() {
        log.info("EXIT {}", applicationName);
    }
}
