package com.genome.dx.core.service;

import com.genome.dx.core.domain.Url;
import com.genome.dx.core.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlService {
    @Autowired
    UrlRepository urlRepository;

    @Cacheable(cacheNames = "urls")
    public List<Url> findAll() {
        return urlRepository.findAll();
    }

}
