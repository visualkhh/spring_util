package com.lgu.cbc.cbpp.service;

import com.lgu.cbc.cbpp.domain.Test;
import com.lgu.cbc.cbpp.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    TestRepository testRepository;

    public void insert(String name) {
        testRepository.save(Test.builder().name(name).build());
    }

    public List<Test> findAll() {
        return testRepository.findAll();
    }
}
