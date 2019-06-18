package com.lgu.cbc.cbpp.repository;

import com.lgu.cbc.cbpp.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;

//https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
public interface TestRepository extends JpaRepository<Test, Integer> {


//	@Modifying
//	@Query(value = "UPDATE T_ADM SET LGIN_FAIL_CNT = LGIN_FAIL_CNT+1 WHERE ADM_SEQ = :admSeq", nativeQuery =true)
////	@Query(nativeQuery =true)
//	Integer pulseLginFailCnt(@Param("admSeq")  Integer admSeq);
//
//	@Modifying
//	@Query(value = "UPDATE T_ADM SET LGIN_FAIL_CNT = LGIN_FAIL_CNT+1 WHERE LGIN_ID = :lginId", nativeQuery =true)
//	Integer setLginFailCnt(@Param("lginId") String lginId);
}
