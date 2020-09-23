package com.genome.dx.core.repository;

import com.genome.dx.core.code.GenderCd;
import com.genome.dx.core.code.ProcStatCd;
import com.genome.dx.core.code.UseCd;
import com.genome.dx.core.domain.PtntInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PtntInfoRepository extends JpaRepository<PtntInfo, Long> {
    @Query(value =
            " SELECT A FROM PtntInfo as A" +
                    " WHERE " +
                    " COALESCE(A.ptntNm,'') like %:ptntNm% " +
                    " AND (:gen is null or A.gen = :gen) " +
                    " AND ((:fromAge is null or A.age >= :fromAge) and (:toAge is null or A.age <= :toAge))" +
                    " AND (:hpoProcStatCd is null or A.hpoProcStatCd = :hpoProcStatCd) " +
                    " AND (:mriProcStatCd is null or A.mriProcStatCd = :mriProcStatCd) " +
                    " AND (:vcfProcStatCd is null or A.vcfProcStatCd = :vcfProcStatCd) " +
                    " AND A.regAdmSeq = :regAdmSeq " +
                    " AND (:useCd is null or A.useCd = :useCd) "
    )
    Page<PtntInfo> findAll(@Param("ptntNm") String ptntNm,
                           @Param("gen") GenderCd gen,
                           @Param("fromAge") Long fromAge,
                           @Param("toAge") Long toAge,
                           @Param("regAdmSeq") Long regAdmSeq,
                           @Param("useCd") UseCd useCd,
                           @Param("hpoProcStatCd") ProcStatCd hpoProcStatCd,
                           @Param("mriProcStatCd") ProcStatCd mriProcStatCd,
                           @Param("vcfProcStatCd") ProcStatCd vcfProcStatCd,
                           Pageable pageable);

    Optional<PtntInfo> findByPtntSeqAndRegAdmSeq(Long ptntSeq, Long regAdmSeq);
}
