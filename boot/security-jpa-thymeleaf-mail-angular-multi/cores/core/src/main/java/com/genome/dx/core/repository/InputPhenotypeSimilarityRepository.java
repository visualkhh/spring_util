package com.genome.dx.core.repository;

import com.genome.dx.core.domain.InputCombinedScore;
import com.genome.dx.core.domain.InputPhenotypeSimilarity;
import com.genome.dx.core.domain.InputVariantSimilarity;
import com.genome.dx.core.domain.base.InputCombinedScoreBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
public interface InputPhenotypeSimilarityRepository extends JpaRepository<InputPhenotypeSimilarity, InputPhenotypeSimilarity.InputPhenotypeSimilarityId> {



//    @Query(value =
//            "" +
//                    " select " +
//                    "    CUSTOM_id          as CASEID, " +
//                    "    PhenotypeScore     as SCORE, " +
//                    "    disease_name       as DIAGNOSIS, " +
//                    "    OMIM_number        as OMIM " +
//                    " from CUSTOM " +
//                    "         inner join (select ComparedEvidence, PhenotypeScore " +
//                    "                     from INPUT_phenotype_similarity " +
//                    "                     where Reference = 'CUS' " +
//                    "                       and INPUT_pid = (select INPUT_pid from INPUT_info where PTNT_SEQ = :ptntSeq) " +
//                    "                     order by PhenotypeScore desc " +
//                    "                     limit :top) as input on CUSTOM.CUSTOM_id = input.ComparedEvidence" +
//                    "",
//            nativeQuery = true)
//    List<Evidence> findPhenoType(@Param("ptntSeq") Long ptntSeq, @Param("top") Long top);

    @Query( "" +
            " SELECT a FROM InputPhenotypeSimilarity a " +
            "   inner join a.inputInfo b " +
            "   inner join a.custom c " +
            " WHERE a.reference = 'CUS' " +
            "   AND a.inputInfo.ptntSeq = :ptntSeq " +
            " ORDER BY a.phenotypeScore DESC  " +
            ""
    )
    Page<InputPhenotypeSimilarity> findByInputPidPtntSeqOrderByPhenotypeScoreDesc(@Param("ptntSeq") Long ptntSeq, Pageable pageable);
}
