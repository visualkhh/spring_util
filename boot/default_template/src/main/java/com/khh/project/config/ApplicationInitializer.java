package com.khh.project.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationInitializer implements ApplicationRunner, ApplicationListener<ApplicationReadyEvent> {

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		log.debug(applicationArguments.toString());
	}
	//https://springframework.guru/running-code-on-spring-boot-startup/
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		log.debug(event.toString());
	}
}
