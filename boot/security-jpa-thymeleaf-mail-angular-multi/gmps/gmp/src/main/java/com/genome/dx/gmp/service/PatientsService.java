package com.genome.dx.gmp.service;

import com.genome.dx.core.code.GenderCd;
import com.genome.dx.core.code.MsgCode;
import com.genome.dx.core.code.ProcStatCd;
import com.genome.dx.core.code.UseCd;
import com.genome.dx.core.domain.PtntInfo;
import com.genome.dx.core.domain.PtntPntype;
import com.genome.dx.core.exception.ErrorMsgException;
import com.genome.dx.core.model.error.Error;
import com.genome.dx.core.repository.PtntInfoRepository;
import com.genome.dx.core.repository.PtntPntypeRepository;
import com.genome.dx.wcore.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatientsService {

    @Autowired
    PtntInfoRepository ptntInfoRepository;

    @Autowired
    PtntPntypeRepository ptntPntypeRepository;

    @Autowired
    SecurityService securityService;

    public Page<PtntInfo> findAll(String ptntNm, GenderCd gen, Long fromAge, Long toAge, UseCd useCd,
                                  ProcStatCd hpoProcStatCd, ProcStatCd mriProcStatCd, ProcStatCd vcfProcStatCd, Pageable pageable) {
        Long regAdmSeq = securityService.getUserDetils().getAdmSeq();
        return  ptntInfoRepository.findAll(ptntNm, gen, fromAge, toAge, regAdmSeq, useCd, hpoProcStatCd, mriProcStatCd, vcfProcStatCd, pageable);
    }
    public PtntInfo findById(Long ptntSeq) {
        Long regAdmSeq = securityService.getUserDetils().getAdmSeq();
        return ptntInfoRepository.findByPtntSeqAndRegAdmSeq(ptntSeq, regAdmSeq).orElseThrow(() -> new ErrorMsgException(new Error(MsgCode.E10003)));
    }

    @Transactional
    public int deletePtntPntypeByPtntSeq(Long ptntSeq) {
        return ptntPntypeRepository.deleteByPtntSeq(ptntSeq);
    }

    public void saveAllPtnPnTypes(List<PtntPntype> ptntPntypes) {
        ptntPntypeRepository.saveAll(ptntPntypes);
    }
    public void savePtnPnTypes(PtntPntype ptntPntype) {
        ptntPntypeRepository.save(ptntPntype);
    }

    public PtntInfo savePtntInfo(PtntInfo ptntInfo) {
        return ptntInfoRepository.save(ptntInfo);
    }
}
