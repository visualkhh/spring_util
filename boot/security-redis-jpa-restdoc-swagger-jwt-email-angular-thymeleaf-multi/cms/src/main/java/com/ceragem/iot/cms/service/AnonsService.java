package com.ceragem.iot.cms.service;

import com.ceragem.iot.core.domain.CoreCode;
import com.ceragem.iot.core.repository.CoreCodeRepository;
import com.ceragem.iot.core.repository.CoreUrlRepository;
import com.omnicns.web.spring.message.CustomReloadableResourceBundleMessageSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AnonsService {

    @Autowired
    private CoreCodeRepository codeRepository;

    @Autowired
    private CoreUrlRepository urlRepository;

    @Autowired
    CacheManager cacheManager;

    @Autowired
    private CustomReloadableResourceBundleMessageSource customReloadableResourceBundleMessageSource;
    public Map<String, String> i18n(String lang) {
        Map<String, String> map =  customReloadableResourceBundleMessageSource.getPropertiesMap();
        Map<String, String> rmap = new LinkedHashMap<>();
        rmap.putAll(map);

        if ("ko_KR".equals(lang) || "ko-KR".equals(lang)) {
            codeRepository.findAll(Sort.by(Sort.Direction.ASC, "cdOrd")).stream().forEach(it -> rmap.put(it.getCd(), it.getCdNm()));
            urlRepository.findAll(Sort.by(Sort.Direction.ASC, "urlSeq")).stream().forEach(it -> rmap.put(it.getI18nCd(), it.getMenuNm()));
        } else { //("en_US".equals(lang))
            codeRepository.findAll(Sort.by(Sort.Direction.ASC, "cdOrd")).stream().forEach(it -> rmap.put(it.getCd(), it.getCdNmEn()));
            urlRepository.findAll(Sort.by(Sort.Direction.ASC, "urlSeq")).stream().forEach(it -> rmap.put(it.getI18nCd(), it.getMenuNmEn()));
        }

        return rmap;
    }

//    @Cacheable(key = "#size", value = "getBoards")
    @Cacheable(cacheNames = "ceragem-codes")
    public List<CoreCode> codes() {
        return codeRepository.findAll();
    }
}
