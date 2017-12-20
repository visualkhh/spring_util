package com.khh.api;

import com.khh.api.config.ProjectConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {ProjectConfig.ROOT_PACKAGE})
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableResourceServer//토큰과 함께 호출하는 API에 대한 것을 검증하는 필터같은 역활을 하게 됩니다.
public class ResourceApplication{
	public static void main(String[] args) {
		SpringApplication.run(ResourceApplication.class, args);
	}
}
