package com.omnicns.medicine.repository;

import com.omnicns.medicine.domain.GameSetDayRst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface GameSetDayRstRepository extends JpaRepository<GameSetDayRst, Integer> {
//    public static final int COMPLET_SET_CNT = 2;
//    public static final int COMPLET_WEEK_CNT = 3;
//    @Query(value =
////            " SELECT new GameSetDayRst(A.userSeq) FROM GameSetDayRst as A " +
////            " SELECT new map(A.userSeq, count(A)) FROM GameSetDayRst as A " +
//            " SELECT new GameSetDayRst(A.userSeq, count(A)) FROM GameSetDayRst as A " +
//                    " WHERE A.date >= :fromDate " +
//                    " AND A.date <= :toDate " +
//                    " AND A.cnt >= " + COMPLET_SET_CNT +
//                    " GROUP BY A.userSeq" +
//                    " HAVING COUNT(A) >= " + COMPLET_WEEK_CNT
////                    " HAVING (:geCnt is null or COUNT(A) >= :geCnt)"
//
//    )
//    List<GameSetDayRst> findSetCompletGroupByUserSeq(
//            @Param("fromDate") ZonedDateTime fromDate,
//            @Param("toDate") ZonedDateTime toDate
//    );
//    @Query(value =
//            " SELECT new GameSetDayRst(A.userSeq, count(A)) FROM GameSetDayRst as A " +
//                    " WHERE A.date >= :date " +
//                    " AND A.date <= :toDate " +
//                    " AND A.cnt >= " + COMPLET_SET_CNT +
//                    " GROUP BY A.userSeq" +
//                    " HAVING COUNT(A) < " + COMPLET_WEEK_CNT
//
//    )
//    List<GameSetDayRst> findSetNotCompletGroupByUserSeq(
//            @Param("date") ZonedDateTime date,
//            @Param("toDate") ZonedDateTime toDate
//    );

    List<GameSetDayRst> findByUserSeq(Integer userSeq);
}
