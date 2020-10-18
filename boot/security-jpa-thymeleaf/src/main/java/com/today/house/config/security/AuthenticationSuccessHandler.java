package com.today.house.config.security;


import com.omnicns.web.request.RequestUtil;
import com.today.house.domain.security.UserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
import java.util.Optional;

//로그인 성공 핸들러
@Slf4j
public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {


    public enum REDIRECT_TYPE{
        USERDETAILS_HOME_URL,
        DEFAULT_SUCCESS_URL,
        SESSION_URL,
        HEADER_REFERER_URL
    }
//    @Autowired
//    ActiveUserStore activeUserStore;
    private RequestCache requestCache = null;
    private RedirectStrategy redirectStrategy = null;
    private REDIRECT_TYPE redirectType = null;

    public AuthenticationSuccessHandler(){
        redirectType        = REDIRECT_TYPE.USERDETAILS_HOME_URL;
        requestCache        = new HttpSessionRequestCache();
        redirectStrategy    = new DefaultRedirectStrategy();
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.debug("onAuthenticationSuccess::"+authentication);
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            LoggedUser user = new LoggedUser(authentication.getName(), activeUserStore);
//            session.setAttribute("user", user);
//        }
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY+"_AUTHENTICATION",authentication);
        switch (redirectType){
            case SESSION_URL            : sendRedirectSessionUrl(request, response); break;
            case HEADER_REFERER_URL     : sendRedirectRefererUrl(request, response); break;
            case USERDETAILS_HOME_URL   : sendRedirectUrl(request, response, ((UserDetails) authentication.getDetails()).getHomeUrl()); break;
            default                     : sendRedirectDefaultUrl(request, response);
        }

    }

    private void sendRedirectSessionUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        String targetUrl = savedRequest.getRedirectUrl();
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private void sendRedirectRefererUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String targetUrl = request.getHeader("REFERER");
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private void sendRedirectUrl(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        redirectStrategy.sendRedirect(request, response, Optional.ofNullable(url).orElse(WebSecurityConfigurerAdapter.DEFAULT_SUCCESS_URL));
    }

    private void sendRedirectDefaultUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        redirectStrategy.sendRedirect(request, response, WebSecurityConfigurerAdapter.DEFAULT_SUCCESS_URL);
    }
}
