package com.ceragem.iot.api.config.security.jwt;

import com.ceragem.iot.api.config.security.WebSecurityConfigurerAdapter;
import com.ceragem.iot.api.model.UserAuthToken;
import com.ceragem.iot.api.repository.UserRepository;
import com.ceragem.iot.api.service.TokenService;
import com.ceragem.iot.core.repository.CoreUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.cache.CacheManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final UserRepository userRepository;
    public SecretKey secretKey;
    public static final String JWT_TOKEN_PROCESSES_URL = WebSecurityConfigurerAdapter.SECURITY_PATH + "/user-sign-in";
    //    private final RedisTemplate<String, Object> redisTemplate;
    private final TokenService tokenService;
    private final CacheManager cacheManager;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager,
                                                      JwtConfig jwtConfig,
                                                      CacheManager cacheManager,
                                                      TokenService tokenService,
                                                      UserRepository userRepository) {
        this.userRepository = userRepository;
        this.cacheManager = cacheManager;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        setFilterProcessesUrl(JWT_TOKEN_PROCESSES_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);

            // 기존 회원은 mysql PASSWORD() 함수를 사용하여 들어가있기떄문에 (취약점발견) 별도로 다시 한번 암호화한다.
            String generatorPassword = userRepository.generatorPassword(authenticationRequest.getPassword());
            authenticationRequest.setPassword(generatorPassword);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );
            Authentication authenticate = authenticationManager.authenticate(authentication);

            return authenticate;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {


        String token = tokenService.makeToken(authResult.getName(), authResult.getAuthorities());
        String fullToken = jwtConfig.getTokenPrefix() + token;
        response.addHeader(HttpHeaders.AUTHORIZATION, fullToken);

        UserAuthToken userAuthToken = UserAuthToken.builder().name(authResult.getName()).refreshToken(tokenService.makeRefreshToken()).token(fullToken).build();
        tokenService.putToken(userAuthToken);
//        tokenService.evictToken(userAuthToken);


        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(userAuthToken));
//        if (null == userAuthToken) {
//            ListOperations<String, Object> stringObjectListOperations = redisTemplate.opsForList();
//            redisTemplate.opsForHash().put("api-user-tokens", authResult.getName(),
//                    UserAuthToken.builder().name(authResult.getName()).refreshToken(refreshtoken).token(fullToken).build()
//            );
//        }
//        List<Code> list = cache.get(SimpleKey.EMPTY, List.class);

//        UUID.randomUUID()
//        Cookie cookie = new Cookie(jwtConfig.getAuthorizationHeader(), token);
//        cookie.setPath("/");
//        response.addCookie(cookie);
    }
}
