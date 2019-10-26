package com.omnicns.medicine.repository;

import com.omnicns.medicine.domain.GameSetRst;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;

public interface GameSetRstRepository extends JpaRepository<GameSetRst, Integer> {
    Page<GameSetRst> findByUserSeqAndMsmtStDtGreaterThanEqualAndMsmtEndDtLessThanEqual(Integer userSeq, ZonedDateTime msmtStDt, ZonedDateTime msmtEndDt, Pageable pageable);
    List<GameSetRst> findByUserSeqAndMsmtStDtGreaterThanEqualAndMsmtEndDtLessThanEqual(Integer userSeq, ZonedDateTime msmtStDt, ZonedDateTime msmtEndDt, Sort sort);
    List<GameSetRst> findByUserSeq(Integer userSeq, Sort sort);
}
