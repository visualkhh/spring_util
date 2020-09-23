package com.genome.dx.core.repository;

//import com.omnicns.medicine.code.AffiliationCd;
//import com.omnicns.medicine.code.UseCd;
//import com.omnicns.medicine.domain.Adm;
import com.genome.dx.core.code.UseCd;
import com.genome.dx.core.domain.Adm;
import com.genome.dx.core.domain.AdmAuth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

//https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
public interface AdmAuthRepository extends JpaRepository<AdmAuth, Long> {
	public int deleteByAdmSeq(Long admSeq);
}
