package com.genome.dx.wcore.service;

import com.genome.dx.core.domain.Term;
import com.genome.dx.core.domain.PhenoType;
import com.genome.dx.core.model.phenoType.PhenoTypeTree;
import com.genome.dx.core.model.phenoType.PhenoTypeTreeForBootstrapTreeView;
import com.genome.dx.core.repository.PhenoTypeRepository;
import com.genome.dx.core.repository.TermRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MetasService {

    @Autowired
    TermRepository termRepository;

    @Autowired
    PhenoTypeRepository phenoTypeRepository;

    @Autowired private CacheManager cacheManager;

//    @Cacheable(cacheNames = "WebCore-MetasService-findTermAll")
//    public List<Term> findTermAll() {
//        List<Term> terms = termRepository.findAll(new Sort(Sort.Direction.ASC, "id"));
//        return terms;
//    }

    @Cacheable(cacheNames = "termsMap")
    public Map<Long, Term> findTermAll() {
        List<Term> terms = termRepository.findAll(new Sort(Sort.Direction.ASC, "id"));
        return terms.stream().collect(Collectors.toMap(it -> it.getId(), it -> it));
    }

    public Map<Long, Term> findTermAllByIds(Collection<Long> ids) {
        List<Term> terms = termRepository.findByIdInOrderByIdAsc(ids);
        return terms.stream().collect(Collectors.toMap(it -> it.getId(), it -> it));
    }

//    @Cacheable(cacheNames = "WebCore-MetasService-findPhenoType")
//    public List<PhenoTypeTree> findPhenoType() {
//        List<PhenoType> phenoTypes = phenoTypeRepository.findAll();
//        List<PhenoTypeTree> phenoTypeTree = new ArrayList<>();
//        phenoTypes.forEach(it -> {
//            PhenoTypeTree p = it.map(PhenoTypeTree.class);
//            if (p.lvl.equals(new Long(1))) {
//                phenoTypeTree.add(p);
//            } else {
//                phenoTypeTree.get(phenoTypeTree.size() - 1).deepAddChild(p);
//            }
//        });
//        return phenoTypeTree;
//    }

    @Cacheable(cacheNames = "phenoTypeTreeView")
    public List<PhenoTypeTreeForBootstrapTreeView> findPhenoTypeForBootStrapTreeView() {
        Cache termsMap = cacheManager.getCache("termsMap");
//        log.debug("cache", termsMap);
        Map<Long, Term> termMap = termsMap.get(SimpleKey.EMPTY, Map.class);
//        log.debug("termsMap", map);
        List<PhenoType> phenoTypes = phenoTypeRepository.findAll();

        List<PhenoTypeTreeForBootstrapTreeView> phenoTypeTree = new ArrayList<>();
        phenoTypes.forEach(it -> {
            Term term = termMap.get(it.termid);
            PhenoTypeTreeForBootstrapTreeView p = new PhenoTypeTreeForBootstrapTreeView(it.termid, term.getName() + " (" + term.getId() + ")", it.parentTermid);
            if (it.lvl.equals(new Long(1))) {
                phenoTypeTree.add(p);
            } else {
                phenoTypeTree.get(phenoTypeTree.size() - 1).deepAddChild(p);
            }
        });
        return phenoTypeTree;
    }

}
