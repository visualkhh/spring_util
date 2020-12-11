package com.company.service.monitor.config;

import com.company.service.monitor.config.properties.ProjectProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@Slf4j
@EnableConfigurationProperties(ProjectProperties.class)
@Configuration
public class CoreConfigration {

    @Autowired
    ProjectProperties projectProperties;


    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setProtocol("smtp");
        javaMailSender.setHost("127.0.0.1");
        javaMailSender.setPort(25);
        return javaMailSender;
    }
    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

}
