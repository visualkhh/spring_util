package com.genome.dx.core.repository;

import com.genome.dx.core.code.GenderCd;
import com.genome.dx.core.code.ProcStatCd;
import com.genome.dx.core.domain.PtntInfo;
import com.genome.dx.core.domain.PtntPntype;
import com.genome.dx.core.domain.base.PtntPntypeBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PtntPntypeRepository extends JpaRepository<PtntPntype, PtntPntypeBase.PtntPntypeId> {

    @Modifying
    @Query("delete from PtntPntype a where a.ptntSeq = :ptntSeq")
    int deleteByPtntSeq(@Param("ptntSeq") Long ptntSeq);
}
