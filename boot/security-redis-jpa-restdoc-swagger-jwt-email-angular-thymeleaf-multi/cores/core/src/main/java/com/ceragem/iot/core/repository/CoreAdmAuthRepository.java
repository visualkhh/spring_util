package com.ceragem.iot.core.repository;

//import com.omnicns.medicine.code.AffiliationCd;
//import com.omnicns.medicine.code.UseCd;
//import com.omnicns.medicine.domain.Adm;
import com.ceragem.iot.core.domain.CoreAdmAuth;
import org.springframework.data.jpa.repository.JpaRepository;

//https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
public interface CoreAdmAuthRepository extends JpaRepository<CoreAdmAuth, Long> {
	public int deleteByAdmSeq(Long admSeq);
}
