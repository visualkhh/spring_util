package com.khh.project.config.web.security;

import com.khh.project.config.web.WebSecurityConfigurerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



//로그인 성공 핸들러
@Slf4j
public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

    @Autowired
    protected AuthenticationManager authenticationManager;
    private RequestCache requestCache = null;
    private RedirectStrategy redirectStrategy = null;

    public AuthenticationSuccessHandler(){
        requestCache        = new HttpSessionRequestCache();
        redirectStrategy    = new DefaultRedirectStrategy();
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.debug("onAuthenticationSuccess::"+authentication);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY+"_AUTHENTICATION",authentication);
        sendRedirectDefaultUrl(request,response);
    }

    private void sendRedirectSessionUrl(HttpServletRequest request,HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        String targetUrl = savedRequest.getRedirectUrl();
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private void sendRedirectRefererUrl(HttpServletRequest request,	HttpServletResponse response) throws IOException {
        String targetUrl = request.getHeader("REFERER");
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private void sendRedirectDefaultUrl(HttpServletRequest request,	HttpServletResponse response) throws IOException {
        redirectStrategy.sendRedirect(request, response, WebSecurityConfigurerAdapter.DEFAULT_SUCCESS_URL);
    }
}
