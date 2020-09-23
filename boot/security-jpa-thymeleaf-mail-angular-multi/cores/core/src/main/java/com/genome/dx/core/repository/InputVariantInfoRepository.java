package com.genome.dx.core.repository;

import com.genome.dx.core.domain.InputVariantInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InputVariantInfoRepository extends JpaRepository<InputVariantInfo, Long> {

    @Query(
            "" +
                    " select a from InputVariantInfo a " +
                    "    inner join fetch a.inputInfo b " +
                    "    inner join fetch a.inputVariant " +
                    " where " +
                    "    a.inputInfo.ptntSeq = :ptntSeq" +
                    " order by " +
                    "    a.tierId asc" +
                    ""
    )
    List<InputVariantInfo> findByInputInfoPtntSeq(@Param("ptntSeq") Long ptntSeq);
}

