package com.genome.dx.wcore.repository;

import com.genome.dx.core.code.BrdCateCd;
import com.genome.dx.wcore.domain.WebCoreBrd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebCoreBrdRepository extends JpaRepository<WebCoreBrd, Integer> {
    List<WebCoreBrd> findByCateCd(BrdCateCd brdCateCd);

    Page<WebCoreBrd> findByTitlContainsAndCateCd(String titl, BrdCateCd brdCateCd, Pageable pageable);
//    Page<Brd> findByTitlContainingAndCateCdOrderByBrdSeqDescGrpOrdAsc(String titl, BrdCateCode catCd, Pageable pageable);
//    Page<Brd> findByTitlContainingAndCateCdAndSvcCdOrderByBrdSeqDescGrpOrdAsc(String titl, BrdCateCode catCd, SvcTypeCode svcTypeCode, Pageable pageable);
//    @Query(value = "SELECT A FROM Brd A where A.titl like %:titl% and A.cateCd = :cateCd and (A.svcCd is null  or A.svcCd = :svcCd) order by A.brdSeq desc, A.grpOrd asc ")
//    Page<Brd> getBrdAllHasSvcCd(
//            @Param("titl") String titl, @Param("cateCd") BrdCateCode cateCd, @Param("svcCd") SvcTypeCode svcCd, Pageable pageable
//    );
//
//
////    List<Brd> findByCateCdAndSvcCdAndUseYnOrderByGrpNoDescGrpOrdAsc(BrdCateCode catCd, SvcTypeCode svcCd, UseYnCode useYn);
////    List<Brd> findByCateCdAndUseYnOrderByGrpNoDescGrpOrdAsc(BrdCateCode catCd, UseYnCode useYn);
//    List<Brd> findByCateCdAndUseYnAndSvcCdIsNullOrderByGrpNoDescGrpOrdAsc(BrdCateCode catCd, UseYnCode useYn);
//    @Query(value = "SELECT A FROM Brd A where A.cateCd = :cateCd and (A.svcCd is null  or A.svcCd = :svcCd) and A.useYn = :useYn order by A.brdSeq desc, A.grpOrd asc ")
//    List<Brd> getBrdAllHasSvcCd(@Param("cateCd") BrdCateCode cateCd, @Param("svcCd") SvcTypeCode svcCd, @Param("useYn") UseYnCode useYn);
//    List<Brd> findByRegDtBetween(ZonedDateTime from, ZonedDateTime to);
}
