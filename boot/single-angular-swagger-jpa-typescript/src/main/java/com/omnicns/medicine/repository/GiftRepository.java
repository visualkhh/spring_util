package com.omnicns.medicine.repository;

import com.omnicns.medicine.code.ComCd;
import com.omnicns.medicine.code.UseCd;
import com.omnicns.medicine.domain.Gift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;

public interface GiftRepository extends JpaRepository<Gift, Integer> {

    @Query(value =
            " SELECT A FROM Gift as A INNER JOIN A.user as B " +
                    " WHERE " +
                    " ((:startDt is null or A.reqDt >= :startDt) and (:endDt is null or A.reqDt <= :endDt))" +
//                    " A.reqDt >= :startDt or A.reqDt <= :endDt " +
                    " AND COALESCE(B.nm,'') like %:nm% " +
                    " AND (:cpon is null or B.cpon = :cpon) " +
                    " AND (:comCd is null or A.comCd = :comCd) " +
                    " AND B.useCd = :useCd "
    )
    Page<Gift> findGift(
            @Param("startDt") ZonedDateTime startDt,
            @Param("endDt") ZonedDateTime endDt,
            @Param("nm") String nm,
            @Param("cpon") String cpon,
            @Param("comCd") ComCd comCd,
            @Param("useCd") UseCd useCd,
            Pageable pageable);
}
