//package com.genome.dx.gmp.repository;
//
//import com.genome.dx.gmp.domain.Evidence;
//import com.genome.dx.gmp.domain.Variant;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface VariantRepository extends JpaRepository<Variant, Long> {
//
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
//    List<Evidence> findPhenoType(@Param("ptntSeq") Long ptntSeq, @Param("evidanceId") Long evidenceId);
//
//}
