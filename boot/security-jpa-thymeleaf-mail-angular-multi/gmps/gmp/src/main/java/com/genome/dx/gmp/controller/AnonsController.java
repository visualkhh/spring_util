package com.genome.dx.gmp.controller;

import com.genome.dx.core.code.MsgCode;
import com.genome.dx.core.domain.Adm;
import com.genome.dx.core.exception.ErrorMsgException;
import com.genome.dx.core.model.error.Error;
import com.genome.dx.core.service.AdmService;
import com.genome.dx.wcore.config.email.WebCoreJavaMailSender;
import com.genome.dx.wcore.config.security.WebSecurityConfigurerAdapter;
import com.genome.dx.wcore.service.AnonsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import java.util.Collection;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(AnonsController.URI_PREFIX)
public class AnonsController {

    public static final String URI_PREFIX 		        = WebSecurityConfigurerAdapter.ANON_PATH;

    @Autowired
    private AnonsService anonService;

    @Autowired
    private AdmService admService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private MessageSourceAccessor messageSource;

    @Value("${project.properties.email-from}")
    String emailFrom;

    @Autowired
    SpringTemplateEngine springTemplateEngine;

    @GetMapping(value="/test")
    public Object test(@RequestParam("names") Collection<String> names){
        return  "wow " + names;
    }

    @GetMapping(value="/d")
    public Object test(@RequestParam("d") int a){
        return  5 / a;
    }

    @GetMapping(value="/w22ow")
    public Object detailz(){
        return new RedirectView("/");
    }

    @GetMapping(value = "/finds/password")
    public void findPassword(
            @RequestParam(name = "admNm") String admNm,
            @RequestParam(name = "admLginId") String admLginId,
            @RequestParam(name = "email") String email
    ) throws MessagingException {
        Adm adm = this.admService.findByAdmLginIdAndAdmNmAndEmail(admLginId, admNm, email);
        if(null == adm) {
            throw new ErrorMsgException(new Error(MsgCode.E10102));
        }
        String newPassword = UUID.randomUUID().toString();
        WebCoreJavaMailSender webCoreJavaMailSender = new WebCoreJavaMailSender(this.javaMailSender);
        Context context = new Context();
        context.setVariable("adm", adm);
        context.setVariable("newPassword", newPassword);

        String html = springTemplateEngine.process("emails/find-password", context);
        webCoreJavaMailSender.sendHtml(emailFrom, adm.getEmail(), messageSource.getMessage("msg.email.find.password.subject"), html);
        adm.setAdmLginPw(bCryptPasswordEncoder.encode(newPassword));
        admService.save(adm);
        //email...
    }

    @GetMapping(value = "/finds/id")
    public void findid(
            @RequestParam(name = "admNm") String admNm,
            @RequestParam(name = "email") String email
    ) throws MessagingException {
        Adm adm = this.admService.findByAdmNmAndEmail(admNm, email);
        if(null == adm) {
            throw new ErrorMsgException(new Error(MsgCode.E10102));
        }
        Context context = new Context();
        context.setVariable("adm", adm);
        String html = springTemplateEngine.process("emails/find-id", context);
        WebCoreJavaMailSender webCoreJavaMailSender = new WebCoreJavaMailSender(this.javaMailSender);
        webCoreJavaMailSender.sendHtml(emailFrom, adm.getEmail(), messageSource.getMessage("msg.email.find.id.subject"), html);
        //email...
    }

}
