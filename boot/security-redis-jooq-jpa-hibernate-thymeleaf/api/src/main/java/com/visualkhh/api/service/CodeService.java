package com.visualkhh.api.service;

import com.visualkhh.api.domain.Code;
import com.visualkhh.api.repository.JCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @EnableCaching
public class CodeService {

    @Autowired private JCodeRepository jcodeRepository;

    @Cacheable(value = "CodeService-codes")
    public List<Code> codes() {
        List<Code> r =  jcodeRepository.codes();
        return r;
    }

    @Cacheable(value = "CodeService-findOne")
    public Code findOne(String code) {
        Code r =  jcodeRepository.findOne(code);
        return r;
    }
}
