package com.omnicns.medicine.repository;

import com.omnicns.medicine.code.PtcpCd;
import com.omnicns.medicine.code.PtcpGrpCd;
import com.omnicns.medicine.code.UseCd;
import com.omnicns.medicine.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByCpon(String cpon);

//    Page<User> findByUseCd(UseCd useCd, Pageable pageable);
//
//    //    @Query("SELECT c FROM Customer c WHERE (:name is null or c.name = :name) and (:email is null"
////            + " or c.email = :email)")
//    // and (:#{#paymentStatuses == null} or ds.statusCode in :paymentStatuses)
////@EntityGraph(value = "some.entity.graph", type = EntityGraph.EntityGraphType.FETCH)
////@EntityGraph(value = "User.gameSetWeekRsts", type = EntityGraph.EntityGraphType.LOAD)
////@EntityGraph(attributePaths = "gameSetWeekRsts")
////    @Query("select o from BusinessObject o where o.owner.emailAddress like "+
////            "?#{hasRole('ROLE_ADMIN') ? '%' : principal.emailAddress}")
//    @Query(value =
//            " SELECT distinct A FROM User as A LEFT JOIN A.gameSetDayRsts as B " +
//                    " WHERE B.date >= :date " +
//                    " AND (:ptcpStDt is null or A.ptcpStDt >= :ptcpStDt) " +
//                    " AND (:ptcpEndDt is null or A.ptcpEndDt >= :ptcpEndDt) " +
//                    " AND (:ptcpCd is null or A.ptcpCd >= :ptcpCd) " +
//                    " AND (:lstSerialNo is null or A.lstSerialNo >= :lstSerialNo) " +
//                    " AND (:cpon is null or A.cpon >= :cpon) " +
//                    " AND (:nm is null or A.nm >= :nm) " +
//                    " AND (:ptcpGrpCd is null or A.ptcpGrpCd >= :ptcpGrpCd) " +
//                    " AND A.useCd = :useCd "
////                    "#{userSeq ?: 'default'}"
////                    " AND A.userSeq in (#{#userSeq != null ? userSeq : 'default'})"
////                    " ?#{userSeqs != null} ? 'AND A.userSeq in ' : userSeq  "
////                    " (#{(#userSeqs != null) ? 'AND A.userSeq in :userSeqs' : ''}) "
////                    " AND (#{#userSeqs != null} ) "
////                    " AND A.userSeq in :userSeqs " +
////                    " AND A.userSeq = #{#userSeqs != null} "
////            ,countQuery = "SELECT count(v) FROM User v "
//
//    )
//    Page<User> findParticipant(
//            @Param("ptcpStDt") ZonedDateTime ptcpStDt,
//            @Param("ptcpEndDt") ZonedDateTime ptcpEndDt,
//            @Param("ptcpCd") PtcpCd ptcpCd,
//            @Param("lstSerialNo") String lstSerialNo,
//            @Param("cpon") String cpon,
//            @Param("nm") String nm,
//            @Param("ptcpGrpCd") PtcpGrpCd ptcpGrpCd,
//            @Param("date") ZonedDateTime date,
//            @Param("useCd") UseCd useCd,
//            Pageable pageable);


    @Query(value =
            " SELECT DISTINCT A FROM User as A LEFT JOIN A.gameSetWeekRsts as B " +
                    " WHERE " +
                    " B.yearweek = :yearweek " +
                    " AND B.conformityCd = 'CFT001' " +
                    " AND (:ptcpStDt is null or A.ptcpStDt >= :ptcpStDt) " +
                    " AND (:ptcpEndDt is null or A.ptcpEndDt <= :ptcpEndDt) " +
                    " AND (:ptcpCd is null or A.ptcpCd = :ptcpCd) " +
                    " AND COALESCE(A.lstSerialNo,'') like %:lstSerialNo% " +
                    " AND (:cpon is null or A.cpon = :cpon) " +
                    " AND COALESCE(A.nm,'') like %:nm% " +
                    " AND (:ptcpGrpCd is null or A.ptcpGrpCd = :ptcpGrpCd) " +
                    " AND (:useCd is null or A.useCd = :useCd) "
    )
    Page<User> findWeekCompletParticipant(
            @Param("ptcpStDt") ZonedDateTime ptcpStDt,
            @Param("ptcpEndDt") ZonedDateTime ptcpEndDt,
            @Param("ptcpCd") PtcpCd ptcpCd,
            @Param("lstSerialNo") String lstSerialNo,
            @Param("cpon") String cpon,
            @Param("nm") String nm,
            @Param("ptcpGrpCd") PtcpGrpCd ptcpGrpCd,
            @Param("yearweek") Integer yearweek,
            @Param("useCd") UseCd useCd,
            Pageable pageable);

    @Query(value =
            " SELECT DISTINCT A FROM User as A LEFT JOIN A.gameSetWeekRsts as B " +
                    " WHERE " +
                    " COALESCE(B.yearweek, :yearweek) = :yearweek " +
                    " AND COALESCE(B.conformityCd, 'CFT002') = 'CFT002' " +
                    " AND (:ptcpStDt is null or A.ptcpStDt >= :ptcpStDt) " +
                    " AND (:ptcpEndDt is null or A.ptcpEndDt <= :ptcpEndDt) " +
                    " AND (:ptcpCd is null or A.ptcpCd = :ptcpCd) " +
                    " AND COALESCE(A.lstSerialNo,'') like %:lstSerialNo% " +
                    " AND (:cpon is null or A.cpon = :cpon) " +
                    " AND COALESCE(A.nm,'') like %:nm% " +
                    " AND (:ptcpGrpCd is null or A.ptcpGrpCd = :ptcpGrpCd) " +
                    " AND (:useCd is null or A.useCd = :useCd) "
    )
    Page<User> findWeekNotCompletParticipant(
            @Param("ptcpStDt") ZonedDateTime ptcpStDt,
            @Param("ptcpEndDt") ZonedDateTime ptcpEndDt,
            @Param("ptcpCd") PtcpCd ptcpCd,
            @Param("lstSerialNo") String lstSerialNo,
            @Param("cpon") String cpon,
            @Param("nm") String nm,
            @Param("ptcpGrpCd") PtcpGrpCd ptcpGrpCd,
            @Param("yearweek") Integer yearweek,
            @Param("useCd") UseCd useCd,
            Pageable pageable);

    @Query(value =
            " SELECT DISTINCT A FROM User as A LEFT JOIN A.gameSetWeekRsts as B " +
                    " WHERE " +
                    " (:ptcpStDt is null or A.ptcpStDt >= :ptcpStDt) " +
                    " AND (:ptcpEndDt is null or A.ptcpEndDt <= :ptcpEndDt) " +
                    " AND (:ptcpCd is null or A.ptcpCd = :ptcpCd) " +
                    " AND COALESCE(A.lstSerialNo,'') like %:lstSerialNo% " +
                    " AND (:cpon is null or A.cpon = :cpon) " +
                    " AND COALESCE(A.nm,'') like %:nm% " +
                    " AND (:ptcpGrpCd is null or A.ptcpGrpCd = :ptcpGrpCd) " +
                    " AND (:useCd is null or A.useCd = :useCd) "
    )
    Page<User> findWeekParticipant(
            @Param("ptcpStDt") ZonedDateTime ptcpStDt,
            @Param("ptcpEndDt") ZonedDateTime ptcpEndDt,
            @Param("ptcpCd") PtcpCd ptcpCd,
            @Param("lstSerialNo") String lstSerialNo,
            @Param("cpon") String cpon,
            @Param("nm") String nm,
            @Param("ptcpGrpCd") PtcpGrpCd ptcpGrpCd,
            @Param("useCd") UseCd useCd,
            Pageable pageable);





    ////////////
//    @Query(value =
//            " SELECT DISTINCT A FROM User as A INNER JOIN A.gameSetDayRsts B " +
//                    " WHERE " +
//                    " (:startDt is null or B.date >= :startDt) " +
//                    " AND (:endDt is null or B.date <= :endDt) " +
//                    " AND (:ptcpCd is null or A.ptcpCd = :ptcpCd) " +
//                    " AND COALESCE(A.lstSerialNo,'') like %:lstSerialNo% " +
//                    " AND (:cpon is null or A.cpon = :cpon) " +
//                    " AND COALESCE(A.nm,'') like %:nm% " +
//                    " AND (:ptcpGrpCd is null or A.ptcpGrpCd = :ptcpGrpCd) " +
//                    " AND A.useCd = :useCd "
//    )

    ///////// nonono
//    @Query(value =
//            " SELECT DISTINCT A FROM User as A JOIN A.gameSetDayRsts as B " +
//                    " WHERE " +
//                    " (:startDt is null or B.date >= :startDt) " +
//                    " AND (:endDt is null or B.date <= :endDt) " +
//                    " AND (:ptcpCd is null or A.ptcpCd = :ptcpCd) " +
//                    " AND COALESCE(A.lstSerialNo,'') like %:lstSerialNo% " +
//                    " AND (:cpon is null or A.cpon = :cpon) " +
//                    " AND COALESCE(A.nm,'') like %:nm% " +
//                    " AND (:ptcpGrpCd is null or A.ptcpGrpCd = :ptcpGrpCd) " +
//                    " AND A.useCd = :useCd "
//    )
//    Page<User> findGameDayRsts(
//            @Param("startDt") ZonedDateTime startDt,
//            @Param("endDt") ZonedDateTime endDt,
//            @Param("ptcpCd") PtcpCd ptcpCd,
//            @Param("lstSerialNo") String lstSerialNo,
//            @Param("cpon") String cpon,
//            @Param("nm") String nm,
//            @Param("ptcpGrpCd") PtcpGrpCd ptcpGrpCd,
//            @Param("useCd") UseCd useCd,
//            Pageable pageable);





    //////////////


//    @Query(value =
//            " SELECT distinct new User(A, 'CFT001') FROM User as A LEFT JOIN A.gameSetDayRsts as B " +
//                    " WHERE B.date >= :fromDate " +
//                    " AND B.date <= :toDate " +
//                    " AND B.cnt >= " + COMPLET_SET_CNT +
//                    " AND (:ptcpStDt is null or A.ptcpStDt >= :ptcpStDt) " +
//                    " AND (:ptcpEndDt is null or A.ptcpEndDt <= :ptcpEndDt) " +
//                    " AND (:ptcpCd is null or A.ptcpCd = :ptcpCd) " +
//                    " AND A.lstSerialNo like %:lstSerialNo% " +
////                    " AND (A.cpon like %:cpon%  OR A.gadCpon like %:cpon%) " +
//                    " AND (:cpon is null or A.cpon = :cpon) " +
//                    " AND A.nm like %:nm% " +
//                    " AND (:ptcpGrpCd is null or A.ptcpGrpCd = :ptcpGrpCd) " +
//                    " AND A.useCd = :useCd " +
//                    " GROUP BY A " +
//                    " HAVING COUNT(A) >= " + COMPLET_WEEK_CNT
//
//    )
//    Page<User> findCompletParticipant(
//            @Param("ptcpStDt") ZonedDateTime ptcpStDt,
//            @Param("ptcpEndDt") ZonedDateTime ptcpEndDt,
//            @Param("ptcpCd") PtcpCd ptcpCd,
//            @Param("lstSerialNo") String lstSerialNo,
//            @Param("cpon") String cpon,
//            @Param("nm") String nm,
//            @Param("ptcpGrpCd") PtcpGrpCd ptcpGrpCd,
//            @Param("fromDate") ZonedDateTime fromDate,
//            @Param("toDate") ZonedDateTime toDate,
//            @Param("useCd") UseCd useCd,
//            Pageable pageable);
//
//
//    @Query(value =
//            " SELECT distinct new User(A, 'CFT002') FROM User as A LEFT JOIN A.gameSetDayRsts as B " +
//                    " WHERE B.date >= :fromDate " +
//                    " AND B.date <= :toDate " +
//                    " AND B.cnt >= " + COMPLET_SET_CNT +
//                    " AND (:ptcpStDt is null or A.ptcpStDt >= :ptcpStDt) " +
//                    " AND (:ptcpEndDt is null or A.ptcpEndDt <= :ptcpEndDt) " +
//                    " AND (:ptcpCd is null or A.ptcpCd = :ptcpCd) " +
//                    " AND A.lstSerialNo like %:lstSerialNo% " +
////                    " AND (A.cpon like %:cpon%  OR A.gadCpon like %:cpon%) " +
//                    " AND (:cpon is null or A.cpon = :cpon) " +
//                    " AND A.nm like %:nm% " +
//                    " AND (:ptcpGrpCd is null or A.ptcpGrpCd = :ptcpGrpCd) " +
//                    " AND A.useCd = :useCd " +
//                    " GROUP BY A " +
//                    " HAVING COUNT(A) < " + COMPLET_WEEK_CNT
//
//    )
//    Page<User> findNotCompletParticipant(
//            @Param("ptcpStDt") ZonedDateTime ptcpStDt,
//            @Param("ptcpEndDt") ZonedDateTime ptcpEndDt,
//            @Param("ptcpCd") PtcpCd ptcpCd,
//            @Param("lstSerialNo") String lstSerialNo,
//            @Param("cpon") String cpon,
//            @Param("nm") String nm,
//            @Param("ptcpGrpCd") PtcpGrpCd ptcpGrpCd,
//            @Param("fromDate") ZonedDateTime fromDate,
//            @Param("toDate") ZonedDateTime toDate,
//            @Param("useCd") UseCd useCd,
//            Pageable pageable);
//
//
//    @Query(value =
//            " SELECT distinct new User(A, CASE WHEN (COUNT(A) >= "+COMPLET_WEEK_CNT+") THEN 'CFT001' ELSE 'CFT002' END ) FROM User as A LEFT JOIN A.gameSetDayRsts as B " +
//                    " ON B.date >= :fromDate " +
//                    " AND B.date <= :toDate " +
//                    " AND B.cnt >= " + COMPLET_SET_CNT +
//                    " WHERE (:ptcpStDt is null or A.ptcpStDt >= :ptcpStDt) " +
//                    " AND (:ptcpEndDt is null or A.ptcpEndDt <= :ptcpEndDt) " +
//                    " AND (:ptcpCd is null or A.ptcpCd = :ptcpCd) " +
//                    " AND COALESCE(A.lstSerialNo,'') like %:lstSerialNo% " +
////                    " AND (A.cpon like %:cpon%  OR A.gadCpon like %:cpon%) " +
//                    " AND (:cpon is null or A.cpon = :cpon) " +
//                    " AND COALESCE(A.nm,'') like %:nm%" +
//                    " AND (:ptcpGrpCd is null or A.ptcpGrpCd = :ptcpGrpCd) " +
//                    " AND A.useCd = :useCd " +
//                    " GROUP BY A "
//
//    )
//    Page<User> findParticipant(
//            @Param("ptcpStDt") ZonedDateTime ptcpStDt,
//            @Param("ptcpEndDt") ZonedDateTime ptcpEndDt,
//            @Param("ptcpCd") PtcpCd ptcpCd,
//            @Param("lstSerialNo") String lstSerialNo,
//            @Param("cpon") String cpon,
//            @Param("nm") String nm,
//            @Param("ptcpGrpCd") PtcpGrpCd ptcpGrpCd,
//            @Param("fromDate") ZonedDateTime fromDate,
//            @Param("toDate") ZonedDateTime toDate,
//            @Param("useCd") UseCd useCd,
//            Pageable pageable);


//    @Query(value =
//            " SELECT distinct A FROM User as A LEFT JOIN A.gameSetWeekRsts as B " +
//            " WHERE A.useCd = :useCd AND B.date >= :date"
//    )
//    List<User> findParticipant(
//            @Param("date") ZonedDateTime date,
//            @Param("useCd") UseCd useCd);
//}
//    @Query(value =
//            " SELECT distinct A FROM User as A LEFT JOIN FETCH A.gameSetWeekRsts as B " +
//            " WHERE A.useCd = :useCd AND B.date >= :date"
//    )
//    List<User> findParticipant(
//            @Param("date") ZonedDateTime date,
//            @Param("useCd") UseCd useCd);
}
