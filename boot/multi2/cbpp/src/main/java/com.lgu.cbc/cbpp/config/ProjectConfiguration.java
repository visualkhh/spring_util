package com.lgu.cbc.cbpp.config;

import com.lgu.cbc.core.config.CoreConfigration;
import com.lgu.cbc.core.config.properties.ProjectProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CoreConfigration.class)
@Slf4j
public class ProjectConfiguration {

    @Autowired
    CoreConfigration coreConfigration;

    public void print() {
        coreConfigration.print();
    }
}
