package com.ceragem.iot.api.controller.securitys;

import com.ceragem.iot.api.domain.User;
import com.ceragem.iot.api.repository.UserRepository;
import com.ceragem.iot.core.code.MsgCode;
import com.ceragem.iot.core.exception.ErrorMsgException;
import com.ceragem.iot.core.model.error.Error;
import com.ceragem.iot.api.config.security.jwt.JwtConfig;
import com.ceragem.iot.api.model.UserAuthToken;
import com.ceragem.iot.api.model.UserRefreshToken;
import com.ceragem.iot.api.service.TokenService;
import com.google.common.net.HttpHeaders;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Slf4j
@RestController
@RequestMapping(SecuritysController.URI_PREFIX)
public class SecuritysController {
    public static final String URI_PREFIX = "/securitys";

    @Autowired
    CacheManager cacheManager;
    @Autowired
    TokenService tokenService;
    @Autowired
    JwtConfig jwtConfig;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = {"", "/"})
    public String securitys() {
        return "securitys";
    }

    @PostMapping(value = "/refresh")
    public UserAuthToken refresh(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestHeader(value= HttpHeaders.AUTHORIZATION) String authorization_header,
            @RequestBody UserRefreshToken refreshToken) {

        String name = null;
        try {
            Jws<Claims> claimsJws = tokenService.parserJwt(authorization_header);
            name = claimsJws.getBody().getSubject();
        } catch (ClaimJwtException e) { // ExpiredJwtException
            name = e.getClaims().getSubject();
        }

        // cache에 있는 값이랑 비교 token, refreshToken
        UserAuthToken userAuthToken = tokenService.getToken(UserAuthToken.builder().name(name).build());
        if (authorization_header.equals(userAuthToken.getToken()) && null != refreshToken && refreshToken.getRefreshToken().equals(userAuthToken.getRefreshToken())) {

            Optional<User> userOption = userRepository.findByNo(Long.parseLong(name));
            User user = userOption.orElseThrow(() -> new ErrorMsgException(new Error(MsgCode.E10003)));

            String token = tokenService.makeToken(Long.toString(user.getNo()), user.getRoles());
            String fullToken = jwtConfig.getTokenPrefix() + token;
            response.addHeader(HttpHeaders.AUTHORIZATION, fullToken);

            UserAuthToken newUserAuthToken = UserAuthToken.builder().name(Long.toString(user.getNo())).refreshToken(tokenService.makeRefreshToken()).token(fullToken).build();
            tokenService.putToken(newUserAuthToken);
            return newUserAuthToken;
        } else {
            throw new ErrorMsgException(new Error(MsgCode.E10003));
        }
//        String token = authorization_header.replace(jwtConfig.getTokenPrefix(), "");
    }


}
