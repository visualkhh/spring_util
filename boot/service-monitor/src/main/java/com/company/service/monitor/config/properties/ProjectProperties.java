package com.company.service.monitor.config.properties;


import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Component
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "projects")
public class ProjectProperties {
    private Map<String, String> properties = new HashMap<>();

    private Config monitor;
    private Config brain;
    private Config mindcares;

    @Getter @Setter @AllArgsConstructor @NoArgsConstructor
    public static class Config {
        String privateKey;
        private Map<String, String> properties = new HashMap<>();
    }

    @Getter @Setter @AllArgsConstructor @NoArgsConstructor
    public static class Mindcare {
        Config kr;
        Config seniorecare;
        Config senioretraining;
        Config medicare;
    }
}

