package com.ceragem.iot.core.repository;

import com.ceragem.iot.core.domain.CorpGrpAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoreCorpGrpAuthRepository extends JpaRepository<CorpGrpAuth, Long> {
    int deleteByCorpGrpSeq(Long corpGrpSeq);
}
