package com.clone.chat.service;

import com.clone.chat.config.security.jwt.JwtConfig;
import com.clone.chat.domain.User;
import com.clone.chat.model.UserToken;
import com.clone.chat.repository.UserRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TokenService {

    @Autowired
    public JwtConfig jwtConfig;
    @Autowired
    public UserRepository userRepository;

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

    public Jws<Claims> parserJwt(String header) throws JwtException {
        String token = header.replace(jwtConfig.getTokenPrefix(), "");
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(this.jwtConfig.getSecretKey().getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token);
        return claimsJws;
    }
    public UserToken parserJwtToUserToken(String header) throws JwtException {
        Jws<Claims> claimsJws = parserJwt(header);
        UserToken userToken = new UserToken();
        Claims body = claimsJws.getBody();
        userToken.setId(body.getSubject());
        List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                .collect(Collectors.toSet());
        userToken.setAuthorities(simpleGrantedAuthorities);
        return userToken;
    }

    public User getUserFromJwtHeader(String header) throws JwtException {
        Jws<Claims> claimsJws = parserJwt(header);
        Optional<User> useroption = userRepository.findById(claimsJws.getBody().getSubject());
        useroption.orElseThrow(() -> new UsernameNotFoundException("notfound"));
        useroption.get().setToken(header);
        return useroption.get();
    }

}
