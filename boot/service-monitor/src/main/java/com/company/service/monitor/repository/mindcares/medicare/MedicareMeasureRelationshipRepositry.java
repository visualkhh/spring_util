package com.company.service.monitor.repository.mindcares.medicare;

import com.company.service.monitor.domain.mindcares.medicare.MeasureRelationship;
import com.company.service.monitor.model.DayCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component("medicare-MeasureRelationshipRepositry")
@Repository
public interface MedicareMeasureRelationshipRepositry extends JpaRepository<MeasureRelationship, Long> {
    @Query(value = "" +
            "SELECT " +
            "   ... " +
            "FROM " +
            "    ( " +
            "       ... " +
            "    ) B LEFT OUTER JOIN ... C " +
            "                        ON ..." +
            "", nativeQuery = true)
    List<DayCount> countByDay(@Param("measureType") String measureType, @Param("day") Integer day);
}
