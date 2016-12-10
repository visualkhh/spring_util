package com.khh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Controller
//@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.khh.project"})
//@Configuration
@SpringBootApplication(scanBasePackages = {Application.BASE_PACKAGES})
@EnableAutoConfiguration
@EnableTransactionManagement
public class Application {
	public static final String BASE_PACKAGES = "com.khh.project";

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}
