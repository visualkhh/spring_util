package com.omnicns.medicine.service;

import com.omnicns.medicine.domain.Adm;
import com.omnicns.medicine.repository.AdmRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service @Slf4j
public class AdminService {

	@Autowired private AdmRepository admRepository;

//	@Transactional
//	public Integer pulseLginFailCnt(Integer admSeq){
//		return admRepository.pulseLginFailCnt(admSeq);
//	}
	public Adm findByAdmLginId(String admLginId){
		return admRepository.findByAdmLginId(admLginId);
	}
	public Adm save(Adm adm){
		return admRepository.save(adm);
	}
//	@Transactional
//	public Integer setLginFailCnt(String lginId){
//		return admRepository.setLginFailCnt(lginId);
//	}

}
