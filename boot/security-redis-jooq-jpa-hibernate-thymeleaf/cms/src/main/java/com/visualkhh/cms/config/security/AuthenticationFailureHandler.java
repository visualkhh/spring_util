package com.visualkhh.cms.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//로그인 실패 핸들러
@Slf4j
public class AuthenticationFailureHandler implements org.springframework.security.web.authentication.AuthenticationFailureHandler {
    @Autowired
    protected AuthenticationManager authenticationManager;
    private RequestCache requestCache = null;
    private RedirectStrategy redirectStrategy = null;

    public AuthenticationFailureHandler() {
        requestCache        = new HttpSessionRequestCache();
        redirectStrategy    = new DefaultRedirectStrategy();
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        redirectStrategy.sendRedirect(request, response, WebSecurityConfigurerAdapter.FAILURE_URL);
    }
}
