package com.ceragem.iot.cms.config;

import com.ceragem.iot.core.config.properties.ProjectProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@Configuration
@Slf4j
@EnableConfigurationProperties(ProjectProperties.class)
// https://docs.spring.io/spring-boot/docs/2.1.10.RELEASE/reference/html/howto-embedded-web-servers.html
// https://spring.io/blog/2016/09/22/new-in-spring-5-functional-web-framework
public class Config {

    @Autowired
    ProjectProperties projectProperties;

}
