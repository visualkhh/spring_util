package com.ceragem.iot.api.config.security.jwt;

import com.google.common.net.HttpHeaders;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@ToString
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Component
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "project.jwt")

public class JwtConfig {

    private String secretKey;
    private String tokenPrefix;
    private Long tokenExpirationAfterDays;

    public JwtConfig() {
    }



//    public String getAuthorizationHeader() {
//        return HttpHeaders.AUTHORIZATION;
//    }
}
