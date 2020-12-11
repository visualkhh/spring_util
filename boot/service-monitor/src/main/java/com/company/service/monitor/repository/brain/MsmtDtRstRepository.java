package com.company.service.monitor.repository.brain;

import com.company.service.monitor.domain.brain.MsmtDtRst;
import com.company.service.monitor.model.DayCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MsmtDtRstRepository extends JpaRepository<MsmtDtRst, Integer> {

    @Query(value = "" +
            "SELECT " +
            "   ... " +
            "FROM " +
            "    ( " +
            "       ... " +
            "    ) B LEFT OUTER JOIN ... C " +
            "                        ON ..." +
            "", nativeQuery = true)
    List<DayCount> countByDay(@Param("day") Integer day);

}
