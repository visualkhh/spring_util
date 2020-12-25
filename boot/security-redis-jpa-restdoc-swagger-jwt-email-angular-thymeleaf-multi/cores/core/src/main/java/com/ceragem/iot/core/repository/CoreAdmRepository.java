package com.ceragem.iot.core.repository;

//import com.omnicns.medicine.code.AffiliationCd;
//import com.omnicns.medicine.code.UseCd;
//import com.omnicns.medicine.domain.Adm;
import com.ceragem.iot.core.code.UseCd;
import com.ceragem.iot.core.domain.CoreAdm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

//https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
public interface CoreAdmRepository extends JpaRepository<CoreAdm, Long> {

	public CoreAdm findAdmByAdmLginId(String admLginId);
	public CoreAdm findByAdmLginId(String admLginId);

	List<CoreAdm> findByUseCdAndEndDtLessThan(UseCd useCd, ZonedDateTime endDt);

	@Deprecated
	Page<CoreAdm> findByCompanyNmContainsAndAdmLginIdContainsAndAdmNmContainsAndUseCdContainsOrderByRegDtDesc(
            String companyNm, String AdmLoginId, String AdmNm, UseCd useCd, Pageable pageable);

//	@EntityGraph(value = "Brd.brds", type = EntityGraph.EntityGraphType.LOAD)
	@Query(value =
			" SELECT A FROM CoreAdm as A" +
					" WHERE " +
					" COALESCE(A.companyNm,'') like %:companyNm% " +
					" AND COALESCE(A.admLginId,'') like %:admLginId% " +
					" AND COALESCE(A.admNm,'') like %:admNm% " +
					" AND (:useCd is null or A.useCd = :useCd) "
//					" AND (NULLIF(:admNm, '') is null or A.admNm = :admNm) " +
//					" AND (NULLIF(:useCd, '') is null or A.useCd = :useCd) "
	)
	Page<CoreAdm> findAll(@Param("companyNm") String companyNm, @Param("admLginId") String admLginId, @Param("admNm") String admNm, @Param("useCd") UseCd useCd, Pageable pageable);

	List<CoreAdm> findByCorpGrpSeq(Long corpGrpSeq);

    CoreAdm findByAdmLginIdAndAdmNmAndEmail(String admNm, String admLoginId, String email);

	CoreAdm findByAdmNmAndEmail(String admNm, String email);


//	@Query(value =
//			" SELECT A FROM Adm as A" +
//					" WHERE " +
//					" COALESCE(A.admLginId,'') like %:admLginId% " +
//					" AND COALESCE(A.admNm,'') like %:admNm% " +
//					" AND (:afftCd is null or A.afftCd = :afftCd) " +
//					" AND (:useCd is null or A.useCd = :useCd) "
//	)
//    Page<Adm> findAll(
//            @Param("admLginId") String admLginId,
//            @Param("admNm") String admNm,
//            @Param("afftCd") AffiliationCd afftCd,
//            @Param("useCd") UseCd useCd,
//            Pageable pageable);
//
//	@Query(value =
//			" SELECT A FROM Adm as A" +
//			" WHERE (:useCd is null or A.useCd = :useCd) "+
//			" AND (A.email is not null) "
//	)
//	List<Adm> getAdminInfo(
//            @Param("useCd") UseCd useCd
//    );


//	Page findAll(String nm, AffiliationCd afftCd, UseCd useCd);


//	@Modifying
//	@Query(value = "UPDATE T_ADM SET LGIN_FAIL_CNT = LGIN_FAIL_CNT+1 WHERE ADM_SEQ = :admSeq", nativeQuery =true)
////	@Query(nativeQuery =true)
//	Integer pulseLginFailCnt(@Param("admSeq")  Integer admSeq);
//
//	@Modifying
//	@Query(value = "UPDATE T_ADM SET LGIN_FAIL_CNT = LGIN_FAIL_CNT+1 WHERE LGIN_ID = :lginId", nativeQuery =true)
//	Integer setLginFailCnt(@Param("lginId") String lginId);
}
