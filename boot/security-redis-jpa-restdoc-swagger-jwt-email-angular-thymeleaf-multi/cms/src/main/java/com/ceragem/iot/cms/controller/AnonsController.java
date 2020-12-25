package com.ceragem.iot.cms.controller;

import com.ceragem.iot.core.code.CrudTypeCd;
import com.ceragem.iot.core.code.MsgCode;
import com.ceragem.iot.core.code.UseCd;
import com.ceragem.iot.core.domain.CoreAdm;
import com.ceragem.iot.core.domain.CoreCode;
import com.ceragem.iot.core.domain.base.AdmBase;
import com.ceragem.iot.core.exception.ErrorMsgException;
import com.ceragem.iot.core.model.error.Error;
import com.ceragem.iot.core.model.view.json.JsonViewFrontEnd;
import com.ceragem.iot.core.service.CoreAdmService;
import com.ceragem.iot.cms.config.security.WebSecurityConfigurerAdapter;
import com.ceragem.iot.wcore.domain.security.AuthDetail;
import com.ceragem.iot.wcore.domain.security.UserDetails;
import com.ceragem.iot.cms.service.AnonsService;
import com.ceragem.iot.cms.service.SecurityService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.*;

@Slf4j
@RestController
@RequestMapping(AnonsController.URI_PREFIX)
public class AnonsController {

    public static final String URI_PREFIX 		        = WebSecurityConfigurerAdapter.ANON_PATH;
    public static final String LANG_CHANGE_URI          = "/langs";
    public static final String LANG_CHANGE_PARAM_NAME   = "lang";

    @Autowired
    private AnonsService anonService;

    @Autowired
    private CoreAdmService admService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private MessageSourceAccessor messageSource;

    @Value("${project.properties.email-from}")
    String emailFrom;

    @Autowired
    SecurityService securityService;

    @Autowired
    SpringTemplateEngine springTemplateEngine;

    @GetMapping(value = "/userDetails")
    @JsonView({JsonViewFrontEnd.class})
    public Object detail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object details = authentication.getDetails();
        return details;
    }

    @GetMapping(value = "/auths")
    @JsonView({JsonViewFrontEnd.class})
    public List<AuthDetail> auth(@RequestParam("path") String path, @RequestParam(value = "crudTypeCd", required = false) List<CrudTypeCd> crudTypeCds) {
        Optional<UserDetails> userDetilsOptional = securityService.getUserDetils();
        List<AuthDetail> rData = new ArrayList<>();
        if (userDetilsOptional.isPresent()) {
            if (null == crudTypeCds || crudTypeCds.size() <= 0) {
                crudTypeCds = Arrays.asList(CrudTypeCd.values());
            }
            rData.addAll(userDetilsOptional.get().getUseAuthorities(path, crudTypeCds));
        }
        return rData;
    }

    @GetMapping(value = "/codes")
    public List<CoreCode> codes(){
        return anonService.codes();
    }

    @PostMapping(value = "/adms")
    @JsonView({JsonViewFrontEnd.class})
    public CoreAdm amd(@RequestBody AdmBase admBase) {
        CoreAdm admed = admService.findByAdmLginId(admBase.getAdmLginId());
        if(null != admed) {
            throw new ErrorMsgException(new Error(MsgCode.E10105, "ID: "+ admed.getAdmLginId() + " " + messageSource.getMessage(MsgCode.E10105.value()) ));
        }
        if (null != admBase.getAdmLginPw() && admBase.getAdmLginPw().length() > 0) {
            admBase.setAdmLginPw(bCryptPasswordEncoder.encode(admBase.getAdmLginPw()));
        } else {
            throw new ErrorMsgException(new Error(MsgCode.E10002, "PASSWORD " + messageSource.getMessage(MsgCode.E10002.value()) ));
        }
        admBase.setUseCd(UseCd.USE002);
        CoreAdm adm = admService.save(admBase.map(CoreAdm.class));
        return adm;
    }


    // 인터셉터에서 lang으로 들어오면 자동으로 변경해준다.
    @GetMapping(value = LANG_CHANGE_URI)
    public Map<String, String> i18n(@RequestParam(LANG_CHANGE_PARAM_NAME) String lang){
        return anonService.i18n(lang);
    }

}
