package com.omnicns.medicine.controller;

import com.omnicns.medicine.domain.Adm;
import com.omnicns.medicine.repository.AdmRepository;
import com.omnicns.medicine.service.AccountService;
import com.omnicns.medicine.model.DataTablePageResponse;
import com.omnicns.medicine.model.DataTablePageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@Slf4j
@RestController
@RequestMapping(AccountController.URI_PREFIX)
public class AccountController {
    public static final String URI_PREFIX 		= "/account";
    @Autowired private AdmRepository userRepository;
    @Autowired
    AccountService accountService;
    @Autowired private MessageSourceAccessor messageSource;

    @Autowired public EntityManager entityManager;

    /* 계정리스트 */
    @RequestMapping(value={"","/"})
    public Page<Adm> getAdm(DataTablePageRequest pageable) {
        Page<Adm> page = accountService.list(pageable);
        DataTablePageResponse dataTablePage = new DataTablePageResponse(page);
        return dataTablePage;
    }
}
