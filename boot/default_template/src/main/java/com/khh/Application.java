package com.khh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Controller
//@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.khh.project"})
//@Configuration
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = {Application.BASE_PACKAGES})
@EnableTransactionManagement
//@EnableResourceServer // API 서버 인증(또는 권한 설정
//@EnableAuthorizationServer // OAuth2 권한 서버
public class Application {
	public static final String BASE_PACKAGES = "com.khh.project";

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}
