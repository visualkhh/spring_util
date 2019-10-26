package com.omnicns.medicine.controller;

import com.omnicns.medicine.domain.Adm;
import com.omnicns.medicine.repository.AdmRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@Slf4j
@RestController
@RequestMapping(AdminController.URI_PREFIX)
public class AdminController {
    public static final String URI_PREFIX 		= "/admin";
    @Autowired private AdmRepository userRepository;
    @Autowired private MessageSourceAccessor messageSource;

    @Autowired public EntityManager entityManager;

    @RequestMapping(value={"","/"})
    public Adm index(){
        Adm a = new Adm();
        a.setAdmNm("a");
        return a;
    }

}
