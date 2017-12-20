package com.visualkhh.cms.controller;

import com.visualkhh.cms.service.AuthService;
import com.omnicns.web.spring.security.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j @EnableCaching
@RestController
@RequestMapping(AuthController.URI_PREFIX)
@Repository
public class AuthController {
    public static final String URI_PREFIX 		= "/auth";
    @Autowired private MessageSourceAccessor messageSource;
    @Autowired private AuthService authService;

    @Autowired public SessionFactory sessionFactory;

    @GetMapping(value={"/",""})
    public SecurityContext context(){
        SecurityContext context = SecurityContextHolder.getContext();
        return context;
    }
    @GetMapping(value="/detail")
    public Object detail(){
        return SecurityUtil.getGrantedAuthorityDetails();
    }
    @GetMapping(value="/gogo")
    public Object gogo(){

        return authService.getGG();
    }

}
