package com.ceragem.iot.core.repository;

import com.ceragem.iot.core.domain.CoreCorpGrp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoreCorpGrpRepository extends JpaRepository<CoreCorpGrp, Long> {

    @EntityGraph(value = "CorpGrp.corpGrpAuths", type = EntityGraph.EntityGraphType.LOAD)
    Page<CoreCorpGrp> findByCorpGrpNmContains(String corpGrpNm, Pageable pageable);

    @EntityGraph(value = "CorpGrp.corpGrpAuths", type = EntityGraph.EntityGraphType.LOAD)
    List<CoreCorpGrp> findByCorpGrpNmContains(String corpGrpNm);

    @EntityGraph(value = "CorpGrp.corpGrpAuths", type = EntityGraph.EntityGraphType.LOAD)
    CoreCorpGrp findByCorpGrpSeq(Long corpGrpSeq);

//    int deleteByCorpGrpSeq(Long corpGrpSeq);
}
