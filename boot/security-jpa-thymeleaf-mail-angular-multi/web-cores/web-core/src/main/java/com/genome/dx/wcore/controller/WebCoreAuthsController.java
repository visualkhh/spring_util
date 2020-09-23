package com.genome.dx.wcore.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.code.ActionCd;
import com.genome.dx.core.code.MsgCode;
import com.genome.dx.core.domain.ActionHistory;
import com.genome.dx.core.domain.Adm;
import com.genome.dx.core.domain.base.AdmBase;
import com.genome.dx.core.exception.ErrorMsgException;
import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
import com.genome.dx.core.repository.ActionHistoryRepository;
import com.genome.dx.wcore.config.security.WebSecurityConfigurerAdapter;
import com.genome.dx.wcore.domain.security.UserDetails;
import com.genome.dx.core.service.AdmService;
import com.genome.dx.wcore.service.SecurityService;
import com.omnicns.web.spring.security.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
import java.util.Arrays;
import java.util.List;


@Slf4j
@RestController
@RequestMapping(WebCoreAuthsController.URI_PREFIX)
@Repository
public class WebCoreAuthsController {

    public static final String URI_PREFIX = WebSecurityConfigurerAdapter.AUTH_PATH + "-web-core";

    @Autowired
    private MessageSourceAccessor messageSource;

    @Autowired
    private AdmService adminService;

    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ActionHistoryRepository actionHIstoryRepository;

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

    @GetMapping(value = "/history")
    public Page<ActionHistory> metas(
            @PageableDefault(sort = { "regDt" }, direction = Sort.Direction.DESC, size = Integer.MAX_VALUE) Pageable pageable,
            @RequestParam(value = "actCds", required = false)List<ActionCd> actionCds) {
        if (null == actionCds || actionCds.size() <= 0) {
            actionCds = Arrays.asList(ActionCd.values());
        }
        return actionHIstoryRepository.findByAdmSeqAndActCdInOrderByRegDtDesc(securityService.getUserDetils().getAdmSeq(), actionCds, pageable);
    }

    @PutMapping(value = "/detail")
    @JsonView({JsonViewFrontEnd.class})
    public Adm updateDetail(@RequestBody AdmBase admBase, HttpServletRequest request, HttpServletResponse response) {
        UserDetails details = (UserDetails) SecurityUtil.getGrantedAuthorityDetails();
        if (admBase.getAdmSeq().equals(details.getAdmSeq())) {
            Adm one = adminService.findOne(details.getAdmSeq()).orElseThrow(() -> new ErrorMsgException(MsgCode.E10102));
            if (null != admBase.getAdmLginPw() && admBase.getAdmLginPw().length() > 0) {
                one.setAdmLginPw(bCryptPasswordEncoder.encode(admBase.getAdmLginPw()));
            }
            one.setAdmNm(admBase.getAdmNm());
//            one.setUseCd(admBase.getUseCd());
//            one.setAfftCd(admBase.getAfftCd());
            one.setHomeUrl(admBase.getHomeUrl());
            one.setEmail(admBase.getEmail());
            one.setUpdDt(ZonedDateTime.now());
            Adm adm = adminService.save(one);
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
