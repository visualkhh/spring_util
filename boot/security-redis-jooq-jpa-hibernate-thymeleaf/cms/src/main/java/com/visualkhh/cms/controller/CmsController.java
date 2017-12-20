package com.visualkhh.cms.controller;

import com.visualkhh.cms.config.security.WebSecurityConfigurerAdapter;
import com.visualkhh.cms.repository.AdmRepository;
import com.visualkhh.cms.repository.FitBrainResultRepository;
import com.visualkhh.cms.config.security.UserDetailsService;
import com.omnicns.web.spring.security.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j @EnableCaching
@Controller
public class CmsController {

    @Autowired private AdmRepository userRepository;
    @Autowired private MessageSourceAccessor messageSource;
    @Autowired
	UserDetailsService userDetailsService;
    @Autowired AdmRepository admRepository;
    @Autowired
	FitBrainResultRepository fitBrainResultRepository;

    @RequestMapping(value={"","/"})
    public String index(
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value="expred", required = false) String expred,
            @RequestParam(value="invalid", required = false) String invalid,
            @RequestParam(value="logout", required = false) String logout,
            ModelMap model){

        Optional.ofNullable(SecurityUtil.getLastExceptionMsg(request)).ifPresent(it->model.put("error",SecurityUtil.getLastExceptionMsg(request)));
        Optional.ofNullable(expred).ifPresent(it->model.put("expred",     messageSource.getMessage("msg.error.login.invalid")));
        Optional.ofNullable(invalid).ifPresent(it->model.put("invalid",    messageSource.getMessage("msg.error.login.expred")));
        Optional.ofNullable(logout).ifPresent(it->model.put("logout",     messageSource.getMessage("msg.error.login.logout")));

        SecurityContext sc = SecurityContextHolder.getContext();
        log.debug("index: {}",sc);
         if(SecurityUtil.isUsernamePasswordAuthentication()){
            model.put("logoutUrl", WebSecurityConfigurerAdapter.LOGOUT_URL);
            return "index";
        }else{
            model.put("loginProcessingUrl", WebSecurityConfigurerAdapter.LOGIN_PROCESSING_URL);
            model.put("usernameParameter", WebSecurityConfigurerAdapter.USERNAME_PARAMETER);
            model.put("passwordParameter", WebSecurityConfigurerAdapter.PASSWORD_PARAMETER);
            return "login";
        }
    }
}
