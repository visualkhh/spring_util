package com.visualkhh.cms.config.security;

import com.omnicns.web.spring.security.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
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
    public static final String FAILURE_URL = WebSecurityConfigurerAdapter.LOGIN_PAGE+"?logfail";

    @Autowired protected AuthenticationManager authenticationManager;
    @Autowired UserDetailsService userDetailsService;
    @Autowired private SessionFactory sessionFactory;
    private RequestCache requestCache;
    private RedirectStrategy redirectStrategy;

    public AuthenticationFailureHandler() {
        requestCache        = new HttpSessionRequestCache();
        redirectStrategy    = new DefaultRedirectStrategy();
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//        adminService.pulseLginFailCnt(userDetails.getAdmSeq());
        String username = request.getParameter(WebSecurityConfigurerAdapter.USERNAME_PARAMETER);
        String password = request.getParameter(WebSecurityConfigurerAdapter.PASSWORD_PARAMETER);
        userDetailsService.pulseLginFailCntByLginId(username);

        //        Integer i = adminService.pulseLginFailCntByLginId(username);
        SecurityUtil.setLastException(request,exception);
        redirectStrategy.sendRedirect(request, response, FAILURE_URL);
    }
}
