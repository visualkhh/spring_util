package com.genome.dx.core.repository;

import com.genome.dx.core.domain.Custom;
import com.genome.dx.core.domain.InputPhenotypeSimilarity;
import com.genome.dx.core.domain.InputVariantSimilarity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
public interface InputVariantSimilarityRepository extends JpaRepository<InputVariantSimilarity, InputVariantSimilarity.InputVariantSimilarityId> {


//    @Query(value =
//            "" +
//                    " select" +
//                    "   CUSTOM_id         as CASEID, " +
//                    "   Variant_Score     as SCORE, " +
//                    "   disease_name      as DIAGNOSIS, " +
//                    "   OMIM_number       as OMIM " +
//                    " from CUSTOM" +
//                    "         inner join (select ComparedEvidence, Variant_Score " +
//                    "                     from INPUT_Variant_similarity " +
//                    "                     where Reference = 'CUS' " +
//                    "                       and INPUT_pid = (select INPUT_pid from INPUT_info where PTNT_SEQ = :ptntSeq) " +
//                    "                     order by Variant_Score desc " +
//                    "                     limit :top) as input on CUSTOM.CUSTOM_id = input.ComparedEvidence " +
//                    "",
//
//            nativeQuery = true)
//    List<Evidence> findVcf(@Param("ptntSeq") Long ptntSeq, @Param("top") Long top);

    @Query( "" +
            " SELECT a FROM InputVariantSimilarity a " +
            "   inner join a.inputInfo b " +
            "   inner join a.custom c " +
            " WHERE a.reference = 'CUS' " +
            "   AND a.inputInfo.ptntSeq = :ptntSeq " +
//            "   AND a.custom.customId = a.comparedEvidence " +
            " ORDER BY a.variantScore DESC  " +
            ""
    )
    Page<InputVariantSimilarity> findByInputPidPtntSeqOrderByVariantScoreDesc(@Param("ptntSeq") Long ptntSeq, Pageable pageable);
}
