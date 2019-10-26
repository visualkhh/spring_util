package com.omnicns.medicine.repository;

import com.omnicns.medicine.domain.GameSetWeekRst;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface GameSetWeekRstRepository extends JpaRepository<GameSetWeekRst, Integer> {
//https://arahansa.github.io/docs_spring/jpa.html
//    Page<GameSetWeekRst> findByUserSeq(Integer userSeq, Pageable pageable);
    List<GameSetWeekRst> findByUserSeq(Integer userSeq);

//    Page<GameSetWeekRst> findByUserSeqAndStartDtGreaterThanEqualAndEndDtLessThanEqual(Integer userSeq, ZonedDateTime startDt, ZonedDateTime endDt, Pageable pageable);
    List<GameSetWeekRst> findByUserSeqAndStartDtGreaterThanEqualAndEndDtLessThanEqual(Integer userSeq, ZonedDateTime startDt, ZonedDateTime endDt);

    @Query(value =
            "SELECT new GameSetWeekRst(A.startDt, A.endDt, A.yearweek, 'CFT002') FROM GameSetWeekRst as A WHERE A.startDt >= :startDt AND A.endDt <= :endDt GROUP BY A.startDt, A.endDt, A.yearweek "
    )
    Page<GameSetWeekRst> findByBetweenWeek(
            @Param("startDt") ZonedDateTime startDt,
            @Param("endDt") ZonedDateTime endDt,
            Pageable pageable);
}
