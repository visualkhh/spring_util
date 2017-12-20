package com.visualkhh.cms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.context.ApplicationListener;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.TimeZone;

@Slf4j @SpringBootApplication @EntityScan(basePackageClasses = {CmsApplication.class, Jsr310JpaConverters.class})
public class CmsApplication implements ApplicationRunner, ApplicationListener<ApplicationReadyEvent>{
	@PostConstruct
	void postConstruct() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		log.debug("postConstruct");

	}
	@PreDestroy
	void preDestory() {
		log.debug("preDestory");
	}

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		log.debug("run>>"+applicationArguments.toString());
	}

	//https://springframework.guru/running-code-on-spring-boot-startup/
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		log.debug("onApplicationEvent>>"+event.toString());
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CmsApplication.class);
		app.addListeners(new ApplicationPidFileWriter());   // pid 를 작성하는 역할을 하는 클래스 선언
		app.run(args);
	}
}
