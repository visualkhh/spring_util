package com.omnicns.medicine.controller;

import com.omnicns.medicine.domain.Adm;
import com.omnicns.medicine.service.AdminService;
import com.omnicns.medicine.service.AuthService;
import com.omnicns.web.spring.security.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping(AuthController.URI_PREFIX)
@Repository
public class AuthController {
    public static final String URI_PREFIX = "/auth";
    @Autowired
    private MessageSourceAccessor messageSource;
    @Autowired
    private AuthService authService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private SessionRegistry sessionRegistry;

    @GetMapping(value = {"/", ""})
    public SecurityContext context() {
        SecurityContext context = SecurityContextHolder.getContext();
        return context;
    }

    @GetMapping(value = "/detail")
    public Object detail() {
        return SecurityUtil.getGrantedAuthorityDetails();
    }

    @PutMapping(value = "/detail")
    public Object updateDetail() {
        return SecurityUtil.getGrantedAuthorityDetails();
    }

    @GetMapping(value = "/currentUsers")
    public Object currentUsers() {
        final List<Object> allPrincipals = sessionRegistry.getAllPrincipals();

//        for(final Object principal : allPrincipals) {
//            if(principal instanceof SecurityUser) {
//                final SecurityUser user = (SecurityUser) principal;
//
//                // Do something with user
//                System.out.println(user);
//            }
//        }
        List<Object> rtn = new ArrayList<>();
        for (final Object principal : allPrincipals) {
            List<SessionInformation> activeUserSessions = sessionRegistry.getAllSessions(principal,/* includeExpiredSessions */ false); // Should not return null;

            if (!activeUserSessions.isEmpty()) {
                rtn.add(principal);
            }
        }
        return rtn;
    }


    @GetMapping(value = "/test/adm")
    public Object testAdm() {
        Adm adm = adminService.findByAdmLginId("omnifit");
        adm.setLginFailCnt(new Integer(55));
        return adminService.save(adm);
    }


}
