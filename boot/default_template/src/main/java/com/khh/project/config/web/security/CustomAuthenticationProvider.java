//package com.khh.project.config.web.security;
//
//import com.libqa.web.domain.User;
//import com.libqa.web.service.user.UserService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @Author : yion
// * @Date : 2015. 4. 18.
// * @Description :
// */
//@Slf4j
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String email = authentication.getName();
//        String password = authentication.getCredentials().toString();
//        String principal = (String) authentication.getPrincipal();
//
//
//        log.info("### CustomAuthenticationProvider email = {}", email);
//        log.info("### CustomAuthenticationProvider password = {}", password);
//        log.info("### CustomAuthenticationProvider principal = {}", principal);
//        User user = userService.findByEmailAndIsCertification(email);
//
//        if (user != null) {
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            boolean isMatchedUser = encoder.matches(password, user.getUserPass());
//            log.info("### isMatchedUser = {}", isMatchedUser);
//            if (isMatchedUser) {
//                List<GrantedAuthority> grantedAuths = new ArrayList<>();
//                grantedAuths.add(new SimpleGrantedAuthority(user.getRole().name()));
//                System.out.println("### grantedAuthority = " + grantedAuths.get(0)); // USER
//                // 최종 방문일 업데이트 함
//                updateUserLastVisitDate(email);
//                return new UsernamePasswordAuthenticationToken(email, password, grantedAuths);
//            } else {
//                log.error("# pwd error ");
//                return null;
//            }
//        } else {
//            log.error("# 로그인 에러 발생 !!! = {}", "사용자 이메일 (" + email + ") 정보를 찾을 수 없습니다.");
//            return null;
//        }
//    }
//
//    private void updateUserLastVisitDate(String email) {
//        log.info("#### 사용자 정보 업데이트");
//        userService.updateUserLastVisitDate(email);
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//
//}
