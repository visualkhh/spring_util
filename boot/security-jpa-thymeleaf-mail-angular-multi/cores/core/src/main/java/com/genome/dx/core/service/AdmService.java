package com.genome.dx.core.service;

import com.genome.dx.core.code.UseCd;
import com.genome.dx.core.domain.Adm;
import com.genome.dx.core.repository.AdmRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AdmService {

    @Autowired
    private AdmRepository admRepository;

    //	@Transactional
//	public Integer pulseLginFailCnt(Integer admSeq){
//		return admRepository.pulseLginFailCnt(admSeq);
//	}
    public Adm findByAdmLginId(String admLginId) {
        return admRepository.findByAdmLginId(admLginId);
    }
    public Optional<Adm> findOne(Long admSeq) {
        return admRepository.findById(admSeq);
    }

    public Adm save(Adm adm) {
        return admRepository.save(adm);
    }

    public List<Adm> findByFinishAdm() {
        return admRepository.findByUseCdAndEndDtLessThan(UseCd.USE001, ZonedDateTime.now());
    }

    public List<Adm> findByCorpGrpSeq(Long corpGrpSeq) {
        return admRepository.findByCorpGrpSeq(corpGrpSeq);
    }

    public Adm findByAdmLginIdAndAdmNmAndEmail(String admLoginId, String admNm, String email) {
        return this.admRepository.findByAdmLginIdAndAdmNmAndEmail(admLoginId, admNm, email);
    }

    public Adm findByAdmNmAndEmail(String admNm, String email) {
        return this.admRepository.findByAdmNmAndEmail(admNm, email);
    }

//    public Adm findOne(Integer seq) {
//        return admRepository.findOne(seq);
//    }
//
//    public void delete(Integer seq) {
//        admRepository.delete(seq);
//    }
//
//    public Page<Adm> findAll(String admLginId, String nm, AffiliationCd afftCd, UseCd useCd, Pageable pageable) {
//        return admRepository.findAll(admLginId, nm, afftCd, useCd, pageable);
//    }
//
//    public List<Adm> getAdminInfo() {
//        //사용
//        return admRepository.getAdminInfo(UseCd.USE001);
//    }
//	@Transactional
//	public Integer setLginFailCnt(String lginId){
//		return admRepository.setLginFailCnt(lginId);
//	}

}
