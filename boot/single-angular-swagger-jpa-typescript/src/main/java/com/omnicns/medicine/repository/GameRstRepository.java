package com.omnicns.medicine.repository;

import com.omnicns.medicine.domain.GameRst;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;

public interface GameRstRepository extends JpaRepository<GameRst, Integer> {
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference
    Page<GameRst> findByUserSeqAndMsmtStDtGreaterThanEqualAndMsmtEndDtLessThanEqual(Integer userSeq, ZonedDateTime msmtStDt, ZonedDateTime msmtEndDt, Pageable pageable);
    Page<GameRst> findByUserSeq(Integer userSeq, Pageable pageable);
//    List<GameRst> findByUserSeqAndMsmtStDtGreaterThanEqualAndMsmtEndDtLessThanEqual(Integer userSeq, ZonedDateTime msmtStDt, ZonedDateTime msmtEndDt);
//    List<GameRst> findByUserSeq(Integer userSeq);
}
