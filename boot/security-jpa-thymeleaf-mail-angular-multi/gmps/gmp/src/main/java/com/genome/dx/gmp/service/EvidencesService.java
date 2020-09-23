package com.genome.dx.gmp.service;

import com.genome.dx.core.code.MsgCode;
import com.genome.dx.core.domain.Custom;
import com.genome.dx.core.domain.InputCombinedScore;
import com.genome.dx.core.domain.InputPhenotypeSimilarity;
import com.genome.dx.core.domain.InputVariantSimilarity;
import com.genome.dx.core.exception.ErrorMsgException;
import com.genome.dx.core.model.error.Error;
import com.genome.dx.core.repository.CustomRepository;
import com.genome.dx.core.repository.InputCombinedScoreRepository;
import com.genome.dx.core.repository.InputPhenotypeSimilarityRepository;
import com.genome.dx.core.repository.InputVariantSimilarityRepository;
import com.genome.dx.core.model.CustomEvidenceScore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EvidencesService {

    @Autowired
    CustomRepository customRepository;

    @Autowired
    InputCombinedScoreRepository inputCombinedScoreRepository;

    @Autowired
    InputPhenotypeSimilarityRepository inputPhenotypeSimilarityRepository;

    @Autowired
    InputVariantSimilarityRepository inputVariantSimilarityRepository;


    public List<CustomEvidenceScore> findAll(Long ptntSeq, Long top, boolean phenoType, boolean vcf) {
        PageRequest pageRequest = PageRequest.of(0, top.intValue());
        List<CustomEvidenceScore> r = new ArrayList<>();
        if (vcf && !phenoType) { //V
            Page<InputVariantSimilarity> data = inputVariantSimilarityRepository.findByInputPidPtntSeqOrderByVariantScoreDesc(ptntSeq, pageRequest);
            r = data.stream().filter(it -> null != it.getCustom()).map(it -> {
                CustomEvidenceScore cec = it.getCustom().map(CustomEvidenceScore.class);
                cec.setScore(it.getVariantScore());
                return cec;
            }).collect(Collectors.toList());
        } else if (phenoType && !vcf) { //P
            Page<InputPhenotypeSimilarity> data = inputPhenotypeSimilarityRepository.findByInputPidPtntSeqOrderByPhenotypeScoreDesc(ptntSeq, pageRequest);
            r = data.stream().filter(it -> null != it.getCustom()).map(it -> {
                CustomEvidenceScore cec = it.getCustom().map(CustomEvidenceScore.class);
                cec.setScore(it.getPhenotypeScore());
                return cec;
            }).collect(Collectors.toList());
        } else if (vcf && phenoType) { //VP
            Page<InputCombinedScore> data = inputCombinedScoreRepository.findByInputPidPtntSeqOrderByCombinedScoreDesc(ptntSeq, pageRequest);
            r = data.stream().filter(it -> null != it.getCustom()).map(it -> {
                CustomEvidenceScore cec = it.getCustom().map(CustomEvidenceScore.class);
                cec.setScore(it.getCombinedScore());
                return cec;
            }).collect(Collectors.toList());
        }
        return r;
    }
//    public List<Evidence> findAll(Long ptntSeq, Long top, boolean phenoType, boolean vcf) {
//        List<Evidence> r = new ArrayList<>();
//        if (vcf && !phenoType) { //V
//            r = evidenceRepository.findVcf(ptntSeq, top);
//        } else if (phenoType && !vcf) { //P
//            r = evidenceRepository.findPhenoType(ptntSeq, top);
//        } else if (vcf && phenoType) { //VP
//            r = evidenceRepository.findVcfAndPhenoType(ptntSeq, top);
//        }
//        return r;
//    }

    public Custom findCustomById(Long evidenceId) {
        return customRepository.findById(evidenceId).orElseThrow(() -> new ErrorMsgException(new Error(MsgCode.E10003)));
    }
}
