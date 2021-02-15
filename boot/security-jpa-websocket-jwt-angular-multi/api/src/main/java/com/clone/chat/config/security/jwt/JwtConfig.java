package com.clone.chat.config.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

//@ToString
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Component
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "project.jwt")
public class JwtConfig {

    @JsonIgnore
    private String secretKey;

    private String tokenPrefix;

    @JsonIgnore
    private Long tokenExpirationAfterDays;

    public JwtConfig() {
    }



//    public String getAuthorizationHeader() {
//        return HttpHeaders.AUTHORIZATION;
//    }
}
