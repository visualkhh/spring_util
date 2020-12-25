package com.ceragem.iot.api.service;

import com.ceragem.iot.api.config.security.jwt.JwtConfig;
import com.ceragem.iot.api.model.UserAuthToken;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class TokenService {
    public final static String TOKEN_KEY = "api-user-tokens";

//    @Autowired
//    public SecretKey secretKey;

    @Autowired
    public JwtConfig jwtConfig;

    @Cacheable(cacheNames = TOKEN_KEY, key="#userAuthToken.name")
    public UserAuthToken getToken(UserAuthToken userAuthToken){
        return userAuthToken;
    }

    @CachePut(cacheNames = TOKEN_KEY, key="#userAuthToken.name")
    public UserAuthToken putToken(UserAuthToken userAuthToken){
        return userAuthToken;
    }

    @CacheEvict(cacheNames = TOKEN_KEY, key="#userAuthToken.name")
    public void evictToken(UserAuthToken userAuthToken){
    }

    public Jws<Claims> parserJwt(String header) throws JwtException {
//        ,
        String token = header.replace(jwtConfig.getTokenPrefix(), "");
        Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(this.jwtConfig.getSecretKey().getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token);
        return claimsJws;
    }

    public String makeToken(String subject, Collection<? extends GrantedAuthority> bodyAuthorities) {
        Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(jwtConfig.getTokenExpirationAfterDays());
//        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(1);
        Date expirationDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

//        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//        Key key = new SecretKeySpec(SignatureAlgorithm.HS512.getJcaName().getBytes());
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.forSigningKey(Key);
//        byte[] keyBytes = this.jwtConfig.getSecretKey().getBytes(StandardCharsets.UTF_8);;
//        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        String token = Jwts.builder()
                .setSubject(subject)
                .claim("authorities", bodyAuthorities)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
//                .signWith(secretKey)
                .signWith(SignatureAlgorithm.HS512, this.jwtConfig.getSecretKey().getBytes(StandardCharsets.UTF_8))
                .compact();
        return token;
    }

    public String makeRefreshToken() {
        String id = UUID.randomUUID().toString();
        Base64.Encoder encoder = Base64.getEncoder();
        String refreshtoken = new String(encoder.encode(id.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        return refreshtoken;
    }

}
