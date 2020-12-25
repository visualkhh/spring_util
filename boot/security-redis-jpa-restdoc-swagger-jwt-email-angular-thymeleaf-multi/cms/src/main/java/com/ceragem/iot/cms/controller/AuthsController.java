package com.ceragem.iot.cms.controller;

import com.ceragem.iot.cms.config.security.WebSecurityConfigurerAdapter;
import com.ceragem.iot.wcore.domain.security.UserDetails;
import com.ceragem.iot.cms.service.SecurityService;
import com.fasterxml.jackson.annotation.JsonView;
import com.ceragem.iot.core.code.MsgCode;
import com.ceragem.iot.core.domain.CoreAdm;
import com.ceragem.iot.core.domain.base.AdmBase;
import com.ceragem.iot.core.exception.ErrorMsgException;
import com.ceragem.iot.core.model.view.json.JsonViewFrontEnd;
import com.ceragem.iot.core.repository.CoreDeviceRepository;
import com.ceragem.iot.core.service.CoreAdmService;
import com.omnicns.web.spring.security.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping(AuthsController.URI_PREFIX)
@Repository
public class AuthsController {

    public static final String URI_PREFIX = WebSecurityConfigurerAdapter.AUTH_PATH;

    @Autowired
    private MessageSourceAccessor messageSource;

    @Autowired
    private CoreAdmService adminService;

    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private CoreDeviceRepository actionHIstoryRepository;

    @GetMapping(value = {"/", ""})
    @JsonView({JsonViewFrontEnd.class})
    public SecurityContext context() {
        SecurityContext context = securityService.getContext();
        return context;
    }

    @GetMapping(value = "/detail")
    @JsonView({JsonViewFrontEnd.class})
    public Object detail() {
        return securityService.getUserDetils();
    }

    @PutMapping(value = "/detail")
    @JsonView({JsonViewFrontEnd.class})
    public CoreAdm updateDetail(@RequestBody AdmBase admBase, HttpServletRequest request, HttpServletResponse response) {
        UserDetails details = (UserDetails) SecurityUtil.getGrantedAuthorityDetails();
        if (admBase.getAdmSeq().equals(details.getAdmSeq())) {
            CoreAdm one = adminService.findOne(details.getAdmSeq()).orElseThrow(() -> new ErrorMsgException(MsgCode.E10102));
            if (null != admBase.getAdmLginPw() && admBase.getAdmLginPw().length() > 0) {
                one.setAdmLginPw(bCryptPasswordEncoder.encode(admBase.getAdmLginPw()));
            }
            one.setAdmNm(admBase.getAdmNm());
//            one.setUseCd(admBase.getUseCd());
//            one.setAfftCd(admBase.getAfftCd());
            one.setCompanyNm(admBase.getCompanyNm());
            one.setHomeUrl(admBase.getHomeUrl());
            one.setEmail(admBase.getEmail());
            one.setUpdDt(ZonedDateTime.now());
            CoreAdm adm = adminService.save(one);
            SecurityUtil.logout(request, response);
            return adm;
            //            return "redirect:/";
        } else {
            throw new ErrorMsgException(MsgCode.E10104);
        }
    }

    @GetMapping(value = "/currentUsers")
    @JsonView({JsonViewFrontEnd.class})
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




}
