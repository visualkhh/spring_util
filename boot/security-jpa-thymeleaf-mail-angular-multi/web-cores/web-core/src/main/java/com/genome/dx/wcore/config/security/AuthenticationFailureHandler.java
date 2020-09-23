package com.genome.dx.wcore.config.security;

import com.genome.dx.core.service.AdmService;
import com.omnicns.web.spring.security.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//로그인 실패 핸들러
@Slf4j
public class AuthenticationFailureHandler implements org.springframework.security.web.authentication.AuthenticationFailureHandler {
    public static final String FAILURE_URL = WebSecurityConfigurerAdapter.LOGIN_PAGE+"?type=sign-fail";

    @Autowired
    protected AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AdmService adminService;
//    private RequestCache requestCache;
    private RedirectStrategy redirectStrategy;

    public AuthenticationFailureHandler() {
//        requestCache        = new HttpSessionRequestCache();
        redirectStrategy    = new DefaultRedirectStrategy();
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//        adminService.pulseLginFailCnt(userDetails.getAdmSeq());
        String username = request.getParameter(WebSecurityConfigurerAdapter.USERNAME_PARAMETER);
//        String password = request.getParameter(WebSecurityConfigurerAdapter.USERPWD_PARAMETER);
//        userDetailsService.setLginFailCnt(username);

        //        Integer i = adminService.setLginFailCnt(username);
        SecurityUtil.setLastException(request,exception);
        redirectStrategy.sendRedirect(request, response, FAILURE_URL);
    }
}
