package com.genome.dx.core.repository;

//import com.omnicns.medicine.code.AffiliationCd;
//import com.omnicns.medicine.code.UseCd;
//import com.omnicns.medicine.domain.Adm;
import com.genome.dx.core.domain.Adm;
import com.genome.dx.core.domain.Custom;
import org.springframework.data.jpa.repository.JpaRepository;

//https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
public interface CustomRepository extends JpaRepository<Custom, Long> {
}
