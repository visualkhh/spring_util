package com.visualkhh.api.service;

import com.visualkhh.api.domain.FitBrain;
import com.visualkhh.api.domain.FitBrainResult;
import com.visualkhh.api.repository.FitBrainRepository;
import com.visualkhh.api.repository.FitBrainResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FitBrainService {

    @Autowired
    private FitBrainResultRepository fitBrainResultRepository;
    @Autowired
    private FitBrainRepository fitBrainRepository;
    @Autowired
    private CodeService codeService;

    /* 두뇌훈련 콘텐츠 리스트 */
    public Collection<FitBrain> getList() {
        Collection<FitBrain> data = fitBrainRepository.findAll(new Sort(Sort.Direction.DESC, "regDt"));
        data.stream().forEach(at -> {
            at.getFitBrainKeywordCd().stream().forEach(it -> {
                it.setFitBrainKeywordCdNm(codeService.findOne(it.getFitBrainKeywordCd()).getCdNm());
            });
        });
        return data;
    }

    /* 두뇌훈련 콘텐츠 상세 */
    public FitBrain getDetail(int fitBrainSeq) {
        FitBrain data = fitBrainRepository.findOne(fitBrainSeq);
        data.getFitBrainKeywordCd().stream().forEach(it-> {
            it.setFitBrainKeywordCdNm(codeService.findOne(it.getFitBrainKeywordCd()).getCdNm());
        });
        return data;
    }

    /* 두뇌훈련 콘텐츠 결과 */
    public FitBrainResult createFitBrainResult(FitBrainResult fitBrainResult){
        return fitBrainResultRepository.save(fitBrainResult);
    }

    public Page<FitBrainResult> findAll(PageRequest pageRequest){
        return fitBrainResultRepository.findAll(pageRequest);
    }
}
