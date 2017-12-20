package com.khh.project.service.security;

import com.khh.project.config.web.WebSecurityConfigurerAdapter;
import groovy.util.logging.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Service
public class SecurityService {

    //수동로그아웃시킬때 사용하세요
    public void logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(new String[]{"remember-me","JSESSIONID",WebSecurityConfigurerAdapter.REMEMBER_ME_COOKE_NAME});
            SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
            cookieClearingLogoutHandler.logout(request, response, (Authentication)null);
            securityContextLogoutHandler.logout(request, response, (Authentication)null);
            request.getSession().invalidate();
        }
    }


}
