package com.genome.dx.core.repository;

import com.genome.dx.core.code.BrdCateCd;
import com.genome.dx.core.code.UseCd;
import com.genome.dx.core.domain.Brd;
import com.genome.dx.core.domain.CorpGrp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorpGrpRepository extends JpaRepository<CorpGrp, Long> {

    @EntityGraph(value = "CorpGrp.corpGrpAuths", type = EntityGraph.EntityGraphType.LOAD)
    Page<CorpGrp> findByCorpGrpNmContains(String corpGrpNm, Pageable pageable);

    @EntityGraph(value = "CorpGrp.corpGrpAuths", type = EntityGraph.EntityGraphType.LOAD)
    List<CorpGrp> findByCorpGrpNmContains(String corpGrpNm);

    @EntityGraph(value = "CorpGrp.corpGrpAuths", type = EntityGraph.EntityGraphType.LOAD)
    CorpGrp findByCorpGrpSeq(Long corpGrpSeq);

//    int deleteByCorpGrpSeq(Long corpGrpSeq);
}
