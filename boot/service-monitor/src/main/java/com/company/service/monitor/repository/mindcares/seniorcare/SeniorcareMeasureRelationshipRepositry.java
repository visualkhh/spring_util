package com.company.service.monitor.repository.mindcares.seniorcare;

import com.company.service.monitor.domain.mindcares.seniorcare.MeasureRelationship;
import com.company.service.monitor.model.DayCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component("seniorcare-MeasureRelationshipRepositry")
@Repository
public interface SeniorcareMeasureRelationshipRepositry extends JpaRepository<MeasureRelationship, Long> {
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
