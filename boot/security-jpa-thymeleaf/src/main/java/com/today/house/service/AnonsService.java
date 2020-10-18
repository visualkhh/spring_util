package com.today.house.service;

import com.omnicns.web.spring.message.CustomReloadableResourceBundleMessageSource;
import com.today.house.domain.Code;
import com.today.house.repository.CodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AnonsService {

    @Autowired
    private CodeRepository codeRepository;
//
//    @Autowired
//    private UrlRepository urlRepository;

    @Autowired
    private CustomReloadableResourceBundleMessageSource customReloadableResourceBundleMessageSource;
    public Map<String, String> i18n(String lang) {
        Map<String, String> map =  customReloadableResourceBundleMessageSource.getPropertiesMap();
        Map<String, String> rmap = new LinkedHashMap<>();
        rmap.putAll(map);

//        if ("ko_KR".equals(lang) || "ko-KR".equals(lang)) {
//            codeRepository.findAll(new Sort(Sort.Direction.ASC, "cdOrd")).stream().forEach(it -> rmap.put(it.getCd(), it.getCdNm()));
//            urlRepository.findAll(new Sort(Sort.Direction.ASC, "urlSeq")).stream().forEach(it -> rmap.put(it.getI18nCd(), it.getMenuNm()));
//        } else { //("en_US".equals(lang))
//            codeRepository.findAll(new Sort(Sort.Direction.ASC, "cdOrd")).stream().forEach(it -> rmap.put(it.getCd(), it.getCdNmEn()));
//            urlRepository.findAll(new Sort(Sort.Direction.ASC, "urlSeq")).stream().forEach(it -> rmap.put(it.getI18nCd(), it.getMenuNmEn()));
//        }

        return rmap;
    }

    public List<Code> codes() {
        return codeRepository.findAll();
    }
}
