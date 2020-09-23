package com.genome.dx.gmp.service;

import com.genome.dx.core.code.MsgCode;
import com.genome.dx.core.domain.InputInfo;
import com.genome.dx.core.domain.InputVariantInfo;
import com.genome.dx.core.domain.Omim;
import com.genome.dx.core.exception.ErrorMsgException;
import com.genome.dx.core.model.error.Error;
import com.genome.dx.core.repository.InputInfoRepository;
import com.genome.dx.core.repository.InputVariantInfoRepository;
import com.genome.dx.core.repository.OmimRepository;
import com.genome.dx.core.model.InputVariantInfoSameCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResultsService {

    @Autowired
    InputInfoRepository inputInfoRepository;

    @Autowired
    InputVariantInfoRepository inputVariantInfoRepository;

    @Autowired
    OmimRepository omimRepository;


    public InputInfo findTopByPtntSeqOrderByInputPidDesc(Long ptntSeq) {
        return inputInfoRepository.findTopByPtntSeqOrderByInputPidDesc(ptntSeq).orElseThrow(() -> new ErrorMsgException(new Error(MsgCode.E10003)));
    }

    public List<InputVariantInfoSameCount> variants(Long ptntSeq, Long evidenceId) {
        List<InputVariantInfoSameCount> rData = new ArrayList<>();
        Optional<Omim> omim = omimRepository.findById(evidenceId);
        List<InputVariantInfo> data = inputVariantInfoRepository.findByInputInfoPtntSeq(ptntSeq);
        data.stream().forEach(it -> {
            InputVariantInfoSameCount newData = it.map(InputVariantInfoSameCount.class);
            if(omim.isPresent() && omim.get().getGeneSymbol().equals(it.getGene())) {
                newData.setSameCount(1l);
            }
            rData.add(newData);
        });
//        inputInfoRepository.findByPtntSeqFetchInputVariantInfoAndInputVariant(ptntSeq);
        return rData;
    }
}
