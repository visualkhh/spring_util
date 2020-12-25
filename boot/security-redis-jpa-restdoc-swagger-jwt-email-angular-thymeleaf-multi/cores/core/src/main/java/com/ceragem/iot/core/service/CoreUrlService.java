package com.ceragem.iot.core.service;

import com.ceragem.iot.core.domain.CoreUrl;
import com.ceragem.iot.core.repository.CoreUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoreUrlService {
    @Autowired
    CoreUrlRepository urlRepository;

    @Cacheable(cacheNames = "urls")
    public List<CoreUrl> findAll() {
        return urlRepository.findAll();
    }

}
