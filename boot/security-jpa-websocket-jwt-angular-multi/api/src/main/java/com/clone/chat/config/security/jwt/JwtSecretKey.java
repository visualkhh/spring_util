package com.clone.chat.config.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JwtSecretKey {

    private final JwtConfig jwtConfig;

    @Autowired
    public JwtSecretKey(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

//    @Bean
//    public SecretKey secretKey() {
//        return Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
//    }
//    @Bean
//    public SecretKey getSigningKey() {
//        byte[] keyBytes = this.jwtConfig.getSecretKey().getBytes(StandardCharsets.UTF_8);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
}
