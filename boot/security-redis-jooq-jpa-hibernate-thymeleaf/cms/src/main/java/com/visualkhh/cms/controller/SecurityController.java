package com.visualkhh.cms.controller;

import com.visualkhh.cms.repository.AdmRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j @EnableCaching
@Controller
@RequestMapping(SecurityController.URI_PREFIX)
public class SecurityController {
    public static final String URI_PREFIX 		= "/security";
    @Autowired private AdmRepository userRepository;
    @Autowired private MessageSourceAccessor messageSource;


}
