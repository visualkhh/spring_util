package com.nhis.ggij.api.cms;

import com.nhis.ggij.api.core.config.CoreConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Controller
//@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.khh.project"})
//@Configuration
//@EnableAuthorizationServer // OAuth2 권한 서버  (토큰을 발행하고, 발행된 토큰을 검증하는 것의 역활은 @EnableAuthorizationServer 어노테이션이 하는 역활)
//API 서버 인증(또는 권한 설정   일반 웹뷰 서버랑 resource서버는 같이갈수없다
//@EnableResourceServer// API서버를 설정하기 위한 설정이다 ,
@EnableAutoConfiguration
@EnableTransactionManagement
//@EnableScheduling
//@SpringBootApplication(scanBasePackages = {CmsApiApplication.ROOT_PACKAGE})
//@SpringBootApplication(scanBasePackages = {CoreConfig.ROOT_PACKAGE, CmsApiApplication.ROOT_PACKAGE})
@SpringBootApplication(scanBasePackages = {CmsApiApplication.ROOT_PACKAGE})
public class CmsApiApplication {
	public static final String ROOT_PACKAGE		= "com.nhis.ggij.api.cms";
	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder(CmsApiApplication.class)
		.profiles("cms")
		.run(args);
	}
}
