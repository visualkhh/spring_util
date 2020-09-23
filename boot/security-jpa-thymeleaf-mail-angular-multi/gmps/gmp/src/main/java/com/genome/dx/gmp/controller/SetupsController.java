package com.genome.dx.gmp.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.code.ActionCd;
import com.genome.dx.core.code.MsgCode;
import com.genome.dx.core.code.UseCd;
import com.genome.dx.core.domain.*;
import com.genome.dx.core.domain.base.PolicyInfoBase;
import com.genome.dx.core.exception.ErrorMsgException;
import com.genome.dx.core.model.error.Error;
import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
import com.genome.dx.core.repository.*;
import com.genome.dx.core.service.AdmService;
import com.genome.dx.wcore.config.http.ProjectMediaType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(SetupsController.URI_PREFIX)
public class SetupsController {

    public static final String URI_PREFIX = "/setups";

    @Autowired
    private AdmRepository admRepository;

    @Autowired
    private AdmAuthRepository admAuthRepository;

    @Autowired
    private CorpGrpRepository corpGrpRepository;

    @Autowired
    private CorpGrpAuthRepository corpGrpAuthRepository;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private ActionHistoryRepository actionHIstoryRepository;

    @Autowired
    private PolicyInfoRepository policyInfoRepository;

    @Autowired
    private AdmService admService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MessageSourceAccessor messageSource;


    @GetMapping(value = "/policies")
    public List<PolicyInfo> policy() {
        return policyInfoRepository.findAll();
    }

    @PostMapping(value = "/policies")
    public PolicyInfo postPolicy(@RequestBody PolicyInfoBase policyInfoBase) {
        return this.policyInfoRepository.save(policyInfoBase.map(PolicyInfo.class));
    }


    @GetMapping(value = "/adms")
    @JsonView({JsonViewFrontEnd.class})
    public Page<Adm> amds(@PageableDefault(sort = {"regDt"}, direction = Sort.Direction.DESC, size = Integer.MAX_VALUE) Pageable pageable,
                          @RequestParam(name = "companyNm", defaultValue = "") String companyNm,
                          @RequestParam(name = "admNm", defaultValue = "") String admNm,
                          @RequestParam(name = "admLginId", defaultValue = "") String admLginId,
                          @RequestParam(name = "useCd", required = false) UseCd useCd
    ) {
        return admRepository.findAll(companyNm, admLginId, admNm, useCd, pageable);
    }

    @GetMapping(value = "/adms/{admSeq}")
    @JsonView({JsonViewFrontEnd.class})
    public Adm amd(@PathVariable(name = "admSeq") Long admSeq) {
        return admRepository.findById(admSeq).orElseThrow(() -> new ErrorMsgException(new Error(MsgCode.E10003)));
    }

    @PostMapping(value = "/adms")
    @JsonView({JsonViewFrontEnd.class})
    @Transactional
    public Adm amd(@RequestBody Adm requestAdm) {
        Adm admed = admService.findByAdmLginId(requestAdm.getAdmLginId());
        if(null != admed) {
            throw new ErrorMsgException(new Error(MsgCode.E10105, "ID: "+ admed.getAdmLginId() + " " + messageSource.getMessage(MsgCode.E10105.value()) ));
        }
        if (null != requestAdm.getAdmLginPw() && requestAdm.getAdmLginPw().length() > 0) {
            requestAdm.setAdmLginPw(bCryptPasswordEncoder.encode(requestAdm.getAdmLginPw()));
        } else {
            throw new ErrorMsgException(new Error(MsgCode.E10002, "PASSWORD " + messageSource.getMessage(MsgCode.E10002.value()) ));
        }
        Adm adm = admService.save(requestAdm.map(Adm.class));
        requestAdm.getAdmAuths().forEach(it -> it.setAdmSeq(adm.getAdmSeq()));
        admAuthRepository.saveAll(requestAdm.getAdmAuths());
        return adm;
    }
    @PutMapping(value = "/adms/{admSeq}")
    @JsonView({JsonViewFrontEnd.class})
    @Transactional
    public Adm amd(@PathVariable(name = "admSeq") Long admSeq, @RequestBody Adm requestAdm) {
        requestAdm.setAdmSeq(admSeq);
        Adm one = admService.findOne(requestAdm.getAdmSeq()).orElseThrow(() -> new ErrorMsgException(MsgCode.E10102));
        if (null != requestAdm.getAdmLginPw() && requestAdm.getAdmLginPw().length() > 0) {
            one.setAdmLginPw(bCryptPasswordEncoder.encode(requestAdm.getAdmLginPw()));
        }
        one.setAdmNm(requestAdm.getAdmNm());
        one.setCompanyNm(requestAdm.getCompanyNm());
        one.setPhone(requestAdm.getPhone());
        one.setEmail(requestAdm.getEmail());
        one.setJobCd(requestAdm.getJobCd());
        one.setHomeUrl(requestAdm.getHomeUrl());
        one.setStartDt(requestAdm.getStartDt());
        one.setEndDt(requestAdm.getEndDt());
        one.setUpdDt(ZonedDateTime.now());
        one.setCorpGrpSeq(requestAdm.getCorpGrpSeq());
        one.setUseCd(requestAdm.getUseCd());
        Adm adm = admService.save(one);

        admAuthRepository.deleteByAdmSeq(adm.getAdmSeq());
        requestAdm.getAdmAuths().forEach(it -> it.setAdmSeq(adm.getAdmSeq()));
        admAuthRepository.saveAll(requestAdm.getAdmAuths());
        return adm;
    }

    @GetMapping(value = "/adms/history/{admSeq}")
    @JsonView({JsonViewFrontEnd.class})
    public Page<ActionHistory> amdHistory(
            @PathVariable(name = "admSeq") Long admSeq,
            @PageableDefault(sort = {"regDt"}, direction = Sort.Direction.DESC, size = Integer.MAX_VALUE) Pageable pageable,
            @RequestParam(value = "actCds", required = false) List<ActionCd> actionCds) {
        if (null == actionCds || actionCds.size() <= 0) {
            actionCds = Arrays.asList(ActionCd.values());
        }
        return actionHIstoryRepository.findByAdmSeqAndActCdInOrderByRegDtDesc(admSeq, actionCds, pageable);
    }

    @GetMapping(value = "/corps")
    @JsonView({JsonViewFrontEnd.class})
    public Page<CorpGrp> corps(@PageableDefault(sort = {"regDt"}, direction = Sort.Direction.DESC, size = Integer.MAX_VALUE) Pageable pageable,
                               @RequestParam(name = "corpGrpNm", defaultValue = "") String corpGrpNm
    ) {
        return corpGrpRepository.findByCorpGrpNmContains(corpGrpNm, pageable);
    }

    @GetMapping(value = "/corps", produces = ProjectMediaType.LIST_JSON_VALUE)
    @JsonView({JsonViewFrontEnd.class})
    public List<CorpGrp> corpsList(@RequestParam(name = "corpGrpNm", defaultValue = "") String corpGrpNm
    ) {
        return corpGrpRepository.findByCorpGrpNmContains(corpGrpNm);
    }

    @GetMapping(value = "/corps/{corpGrpSeq}")
    @JsonView({JsonViewFrontEnd.class})
    public CorpGrp corps(@PathVariable(name = "corpGrpSeq") Long corpGrpSeq) {
        return corpGrpRepository.findById(corpGrpSeq).orElseThrow(() -> new ErrorMsgException(new Error(MsgCode.E10003)));
    }
    @PostMapping(value = "/corps")
    @JsonView({JsonViewFrontEnd.class})
    @Transactional
    public CorpGrp corps(@RequestBody CorpGrp corpGrp) {
        corpGrp = corpGrpRepository.save(corpGrp);
        for(int i = 0; null != corpGrp.getCorpGrpAuths() && corpGrp.getCorpGrpAuths().size() > i ; i++) {
            CorpGrpAuth it = corpGrp.getCorpGrpAuths().get(i);
            CorpGrpAuth corpGrpAuth = new CorpGrpAuth();
            corpGrpAuth.setCorpGrpSeq(corpGrp.getCorpGrpSeq());
            corpGrpAuth.setAuthId(it.getAuthId());
            corpGrpAuthRepository.save(corpGrpAuth);
        }
        return corpGrp;
    }
    @PutMapping(value = "/corps/{corpGrpSeq}")
    @JsonView({JsonViewFrontEnd.class})
    @Transactional
    public void corps(@PathVariable(name = "corpGrpSeq") Long corpGrpSeq, @RequestBody CorpGrp corpGrp) {
        CorpGrp dbCorpGrp = corpGrpRepository.findById(corpGrpSeq).orElseThrow(() -> new ErrorMsgException(new Error(MsgCode.E10003)));
        dbCorpGrp.setCorpGrpNm(corpGrp.getCorpGrpNm());
        dbCorpGrp = corpGrpRepository.save(dbCorpGrp);
        int deleteSize = corpGrpAuthRepository.deleteByCorpGrpSeq(corpGrpSeq);
        for(int i = 0; null != corpGrp.getCorpGrpAuths() && corpGrp.getCorpGrpAuths().size() > i ; i++) {
            CorpGrpAuth it = corpGrp.getCorpGrpAuths().get(i);
            CorpGrpAuth corpGrpAuth = new CorpGrpAuth();
            corpGrpAuth.setCorpGrpSeq(corpGrpSeq);
            corpGrpAuth.setAuthId(it.getAuthId());
            corpGrpAuthRepository.save(corpGrpAuth);
        }
    }

    @DeleteMapping(value = "/corps/{corpGrpSeq}")
    @JsonView({JsonViewFrontEnd.class})
    @Transactional
    public void deleteCorps(@PathVariable(name = "corpGrpSeq") Long corpGrpSeq) {
        List<Adm> adms = Optional.ofNullable(admService.findByCorpGrpSeq(corpGrpSeq)).orElse(Collections.emptyList());
        if (adms.size() > 0) {
            String ids = adms.stream().map(it -> it.getAdmLginId()).collect(Collectors.joining(","));
            throw new ErrorMsgException(new Error(MsgCode.E99999, ids+" Role First Remove"));
        }
        int deleteSize = corpGrpAuthRepository.deleteByCorpGrpSeq(corpGrpSeq);
        corpGrpRepository.deleteById(corpGrpSeq);
    }


    @GetMapping(value = "/auths")
    @JsonView({JsonViewFrontEnd.class})
    public List<Auth> auths(@PageableDefault(sort = {"regDt"}, direction = Sort.Direction.DESC, size = Integer.MAX_VALUE) Pageable pageable,
                            @RequestParam(name = "corpGrpNm", defaultValue = "") String corpGrpNm
    ) {
        return authRepository.findAll();
    }

    @GetMapping(value = "/auths/{authId}")
    @JsonView({JsonViewFrontEnd.class})
    public Auth auths(@PathVariable(name = "authId") String authId) {
        return authRepository.findById(authId).orElseThrow(() -> new ErrorMsgException(new Error(MsgCode.E10003)));
    }
}
