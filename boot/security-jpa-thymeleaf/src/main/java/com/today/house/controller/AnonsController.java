package com.today.house.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.today.house.code.CrudTypeCd;
import com.today.house.config.security.WebSecurityConfigurerAdapter;
import com.today.house.domain.Code;
import com.today.house.domain.security.AuthDetail;
import com.today.house.domain.security.UserDetails;
import com.today.house.model.view.json.JsonViewFrontEnd;
import com.today.house.service.AnonsService;
import com.today.house.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping(AnonsController.URI_PREFIX)
public class AnonsController {

    public static final String URI_PREFIX 		        = WebSecurityConfigurerAdapter.ANON_PATH;
    public static final String LANG_CHANGE_URI          = "/langs";
    public static final String LANG_CHANGE_PARAM_NAME   = "lang";

    @Autowired
    SecurityService securityService;

    @Autowired
    private AnonsService anonService;

    @Autowired
    private MessageSourceAccessor messageSource;

    @GetMapping(value = {"/",""})
    public SecurityContext context(){
        SecurityContext context = SecurityContextHolder.getContext();
        return context;
    }

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
    public List<Code> codes(){
        return anonService.codes();
    }
//
//    @GetMapping(value="/notices")
//    @JsonView({JsonViewFrontEnd.class})
//    public Page<Brd> notice(
//            @PageableDefault(sort = { "regDt" }, direction = Sort.Direction.DESC, size = Integer.MAX_VALUE) Pageable pageable,
//            @RequestParam(name = "search", defaultValue = "") String titl) {
//        Page<Brd> datas = brdRepository.findByTitlContainsAndCateCdAndUseCdOrderByBrdSeqDesc(titl, BrdCateCd.BCC001, UseCd.USE001, pageable);
//        return datas;
//    }
//
//    @GetMapping(value="/policies")
//    public List<PolicyInfo> policy(){
//        return policyInfoRepository.findAll();
//    }
//
//
//    @PostMapping(value = "/adms")
//    @JsonView({JsonViewFrontEnd.class})
//    public Adm amd(@RequestBody AdmBase admBase) {
//        Adm admed = admService.findByAdmLginId(admBase.getAdmLginId());
//        if(null != admed) {
//            throw new ErrorMsgException(new Error(MsgCode.E10105, "ID: "+ admed.getAdmLginId() + " " + messageSource.getMessage(MsgCode.E10105.value()) ));
//        }
//        if (null != admBase.getAdmLginPw() && admBase.getAdmLginPw().length() > 0) {
//            admBase.setAdmLginPw(bCryptPasswordEncoder.encode(admBase.getAdmLginPw()));
//        } else {
//            throw new ErrorMsgException(new Error(MsgCode.E10002, "PASSWORD " + messageSource.getMessage(MsgCode.E10002.value()) ));
//        }
//        admBase.setUseCd(UseCd.USE002);
//        Adm adm = admService.save(admBase.map(Adm.class));
//        return adm;
//    }


    // 인터셉터에서 lang으로 들어오면 자동으로 변경해준다.
    @GetMapping(value = LANG_CHANGE_URI)
    public Map<String, String> i18n(@RequestParam(LANG_CHANGE_PARAM_NAME) String lang){
        return anonService.i18n(lang);
    }

}
