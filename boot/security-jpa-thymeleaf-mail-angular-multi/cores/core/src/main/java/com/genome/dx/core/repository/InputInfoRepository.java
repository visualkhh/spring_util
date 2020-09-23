package com.genome.dx.core.repository;

import com.genome.dx.core.code.BrdCateCd;
import com.genome.dx.core.code.UseCd;
import com.genome.dx.core.domain.Brd;
import com.genome.dx.core.domain.InputInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InputInfoRepository extends JpaRepository<InputInfo, Long> {
    Optional<InputInfo> findTopByPtntSeqOrderByInputPidDesc(Long aLong);

//    @Query(
//            "select distinct a from InputInfo as a inner join fetch a.variantInfos b where a.ptntSeq = :ptntSeq order by a.variantInfos.tierId asc"
//    )
//    void findByPtntSeqFetchInputVariantInfoAndInputVariant(@Param("ptntSeq") Long ptntSeq);
}

