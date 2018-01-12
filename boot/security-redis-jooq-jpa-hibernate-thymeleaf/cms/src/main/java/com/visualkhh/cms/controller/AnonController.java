package com.visualkhh.cms.controller;

import com.omnicns.web.spring.message.CustomReloadableResourceBundleMessageSource;
import com.visualkhh.cms.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j @EnableCaching
@RestController
@RequestMapping(AnonController.URI_PREFIX)
public class AnonController {
    public static final String URI_PREFIX 		= "/anon";
    @Autowired private MessageSourceAccessor messageSource;
    @Autowired private AuthService authService;
//    @Autowired private CustomPropertiesPersistor customPropertiesPersistor;
    @Autowired private CustomReloadableResourceBundleMessageSource customReloadableResourceBundleMessageSource;
    @Autowired private SessionRegistry sessionRegistry;
    @Autowired private SessionFactory sessionFactory;

    public static final String LANG_CHANGE_URI = "/langs";
    public static final String LANG_CHANGE_PARAM_NAME = "lang";

    @GetMapping(value={"/",""})
    public SecurityContext context(){
        SecurityContext context = SecurityContextHolder.getContext();
        return context;
    }

    //인터셉터에서 lang으로 들어오면 자동으로 변경해준다.
    @GetMapping(value=LANG_CHANGE_URI)
    public Object i18n(@RequestParam(LANG_CHANGE_PARAM_NAME) String lang){
//
        return customReloadableResourceBundleMessageSource.getPropertiesMap();
//        return customPropertiesPersistor.getData();
////        MessageSourceAware
//        MessageResolveService
//        originMessageSource.getParentMessageSource().
//
//        messageSource.getMessage("");
//        adminService.pulseLginFailCnt(1);
//        return "";
    }

}
