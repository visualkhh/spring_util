package com.omnicns.medicine.controller;

import com.omnicns.medicine.config.security.UserDetailsService;
import com.omnicns.medicine.domain.security.UserDetails;
import com.omnicns.medicine.repository.AdmRepository;
import com.omnicns.web.spring.security.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j

@Controller
public class CmsController {

    @Autowired
    private AdmRepository userRepository;
    @Autowired
    private MessageSourceAccessor messageSource;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    AdmRepository admRepository;

    @RequestMapping(value = {"", "/"})
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
//        if(AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
//            return "index";
//        } else if(UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
//            return "redirect:"+((UserDetails)authentication.getDetails()).getHomeUrl();
//        }
        return "index";
    }
////    @RequestMapping(value = {"", "/"}, params = {"type=details"},produces = MediaType.APPLICATION_JSON_VALUE)
//    @RequestMapping(value = {"", "/"}, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public Object details(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication authentication = context.getAuthentication();
//        return authentication.getDetails();
//    }

//    @RequestMapping(value={"","/"})
//    public String index(
//            HttpServletRequest request, HttpServletResponse response,
//            @RequestParam(value="expred", required = false) String expred,
//            @RequestParam(value="invalid", required = false) String invalid,
//            @RequestParam(value="logout", required = false) String logout,
//            @RequestParam(value="logfail", required = false) String logfail,
//            //@RequestHeader(value= HttpHeaders.ACCEPT_LANGUAGE, required = false) String acceptLanguage,
//            ModelMap model){
//
//
//
////        Optional.ofNullable(SecurityUtil.getLastExceptionMsg(request)).ifPresent(it->model.put("error",SecurityUtil.getLastExceptionMsg(request)));
////        Optional.ofNullable(expred).ifPresent(it->model.put("expred",   messageSource.getMessage("msg.error.login.invalid")));
////        Optional.ofNullable(invalid).ifPresent(it->model.put("invalid", messageSource.getMessage("msg.error.login.expred")));
////        Optional.ofNullable(logout).ifPresent(it->model.put("logout",   messageSource.getMessage("msg.error.login.logout")));
//        Optional.ofNullable(expred).ifPresent(it->model.put("expred",   "login invalid"));
//        Optional.ofNullable(invalid).ifPresent(it->model.put("invalid", "login.expred"));
//        Optional.ofNullable(logout).ifPresent(it->model.put("logout",   "logout"));
////        Optional.ofNullable(logfail).ifPresent(it->model.put("logfail",  messageSource.getMessage("msg.error.login.fail")));
//
//        SecurityContext sc = SecurityContextHolder.getContext();
//        log.debug("index: {}",sc);
//         if(SecurityUtil.isUsernamePasswordAuthentication()){
//            model.put("logoutUrl", WebSecurityConfigurerAdapter.LOGOUT_URL);
//            return "index";
//        }else{
//            model.put("loginProcessingUrl", WebSecurityConfigurerAdapter.LOGIN_PROCESSING_URL);
//            model.put("usernameParameter", WebSecurityConfigurerAdapter.USERNAME_PARAMETER);
//            model.put("passwordParameter", WebSecurityConfigurerAdapter.USERPWD_PARAMETER);
//            return "login";
//        }
//    }
}
