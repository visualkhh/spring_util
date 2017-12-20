package com.khh.api;

import com.khh.api.config.ProjectConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication(scanBasePackages = {ProjectConfig.ROOT_PACKAGE})
@EnableAutoConfiguration
@EnableAuthorizationServer //oauth2 토큰을 발행하고, 발행된 토큰을 검증하는 것의 역활
public class AuthApplication{
	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}
}
