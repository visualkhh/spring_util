package com.company.service.monitor.repository.mindcares.kr;

import com.company.service.monitor.domain.mindcares.kr.MeasureRelationship;
import com.company.service.monitor.model.DayCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// https://stackoverflow.com/questions/29082749/spring-data-jpa-map-the-native-query-result-to-non-entity-pojo
@Repository("mindcarekr-MeasureRelationshipRepositry")
public interface MindcareKRMeasureRelationshipRepositry extends JpaRepository<MeasureRelationship, Long> {
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
