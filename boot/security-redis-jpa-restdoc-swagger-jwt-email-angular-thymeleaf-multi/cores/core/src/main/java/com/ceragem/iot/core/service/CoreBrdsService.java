package com.ceragem.iot.core.service;

import com.ceragem.iot.core.domain.CoreArticleContent;
import com.ceragem.iot.core.repository.CoreArticleContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoreBrdsService {
    @Autowired
    CoreArticleContentRepository coreArticleContentRepository;

    public List<CoreArticleContent> findAll() {
        return coreArticleContentRepository.findAll();
    }

    public Optional<CoreArticleContent> findById(Long no) {
        return coreArticleContentRepository.findById(no);
    }
}
