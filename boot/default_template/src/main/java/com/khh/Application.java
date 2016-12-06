package com.khh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Controller
//@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.khh.project"})
//@Configuration
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = {Application.BASE_PACKAGES})
@EnableTransactionManagement
public class Application {
	public static final String BASE_PACKAGES = "com.khh.project";

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}
