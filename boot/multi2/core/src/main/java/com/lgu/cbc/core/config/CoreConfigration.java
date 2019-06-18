package com.lgu.cbc.core.config;

import com.lgu.cbc.core.config.properties.ProjectProperties;
import com.lgu.cbc.core.ha.HaChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@EnableConfigurationProperties(ProjectProperties.class)
public class CoreConfigration {
    @Autowired
    ProjectProperties projectProperties;

    public void print() {
        log.debug(this.projectProperties.getProperties().toString());
    }

    @Bean
    public HaChecker haChecker() {
        return new HaChecker(projectProperties.getProperties().get("haIp"), Integer.parseInt(projectProperties.getProperties().get("haPort")));
    }
}
