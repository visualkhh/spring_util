package com.omnicns.medicine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.context.ApplicationListener;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.TimeZone;


@Slf4j @EnableBatchProcessing @SpringBootApplication
// 2017-12-12 10:38:40.030|ERROR|main|org.hibernate.AssertionFailure|<init>[33]|HHH000099: an assertion failure occured (this may indicate a bug in Hibernate, b
// ut is more likely due to unsafe use of the session): org.hibernate.AssertionFailure: AttributeConverter class [class org.springframework.data.jpa.convert.thr
//		eeten.Jsr310JpaConverters$InstantConverter] registered multiple times
// 위에 에러로 DEV서버에서 구동되지 않아 entityscan 주석처리 후
// gradle에 compile("org.hibernate:hibernate-java8") 추가
public class MedicineApplication implements ApplicationRunner, ApplicationListener<ApplicationReadyEvent>{
	@Value("${project.properties.salt}")
	String salt;
	@Value("${project.properties.private-key}")
	String privateKey;

	@PostConstruct
	void postConstruct() {
//		log.debug("old redis keys * list "+redisTemplate.keys("*"));
//		redisTemplate.keys("*").stream().forEach(k-> {
//			redisTemplate.delete(k);
//		});
//		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
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
		SpringApplication app = new SpringApplication(MedicineApplication.class);
		app.addListeners(new ApplicationPidFileWriter());   // pid 를 작성하는 역할을 하는 클래스 선언
		app.run(args);
	}
}
