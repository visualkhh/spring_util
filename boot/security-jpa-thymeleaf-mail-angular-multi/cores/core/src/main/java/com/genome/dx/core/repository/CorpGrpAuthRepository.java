package com.genome.dx.core.repository;

import com.genome.dx.core.domain.CorpGrp;
import com.genome.dx.core.domain.CorpGrpAuth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorpGrpAuthRepository extends JpaRepository<CorpGrpAuth, Long> {
    int deleteByCorpGrpSeq(Long corpGrpSeq);
}
