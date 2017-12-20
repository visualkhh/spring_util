package com.visualkhh.cms.controller;

import com.visualkhh.cms.domain.Adm;
import com.visualkhh.cms.repository.AdmRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j @EnableCaching
@RestController
@RequestMapping(AdminController.URI_PREFIX)
public class AdminController {
    public static final String URI_PREFIX 		= "/admin";
    @Autowired private AdmRepository userRepository;
    @Autowired private MessageSourceAccessor messageSource;

    @Autowired public SessionFactory sessionFactory;

    @RequestMapping(value={"","/"})
    public Adm index(){
        Adm a = new Adm();
        a.setAdmNm("a");
        return a;
    }

}
