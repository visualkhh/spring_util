package com.omnicns.medicine.repository;

import com.omnicns.medicine.domain.Adm;
import org.springframework.data.jpa.repository.JpaRepository;

//https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
public interface AdmRepository extends JpaRepository<Adm, Integer> {

	public Adm findAdmByAdmLginId(String admLginId);
	public Adm findByAdmLginId(String admLginId);

//	@Modifying
//	@Query(value = "UPDATE T_ADM SET LGIN_FAIL_CNT = LGIN_FAIL_CNT+1 WHERE ADM_SEQ = :admSeq", nativeQuery =true)
////	@Query(nativeQuery =true)
//	Integer pulseLginFailCnt(@Param("admSeq")  Integer admSeq);
//
//	@Modifying
//	@Query(value = "UPDATE T_ADM SET LGIN_FAIL_CNT = LGIN_FAIL_CNT+1 WHERE LGIN_ID = :lginId", nativeQuery =true)
//	Integer setLginFailCnt(@Param("lginId") String lginId);
}
