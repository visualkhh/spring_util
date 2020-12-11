package com.company.service.monitor.repository.mindcares.kr;

import com.company.service.monitor.domain.mindcares.kr.Manual;
import com.company.service.monitor.model.ManyMeasUser;
import com.company.service.monitor.model.MeasCrop;
import com.company.service.monitor.model.MeasUserScope;
import com.company.service.monitor.model.SignUpUserScope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// https://stackoverflow.com/questions/29082749/spring-data-jpa-map-the-native-query-result-to-non-entity-pojo
@Repository
public interface MindcareKRManualRepositry extends JpaRepository<Manual, Long> {
    @Query(value = "" +
            "SELECT " +
            "    ... " +
            "FROM " +
            "    ( " +
            "        ... " +
            "    ) C" +
            "", nativeQuery = true)
    SignUpUserScope totalSignUpUser();

    @Query(value = "" +
            "SELECT " +
            "    .... " +
            "FROM " +
            "    ( " +
            "        ..." +
            "    ) C" +
            "", nativeQuery = true)
    SignUpUserScope afterMonthSignUpUser();

    @Query(value = "" +
            "SELECT " +
            "    ... " +
            "FROM " +
            "    ( " +
            "       ... " +
            "    ) C" +
            "", nativeQuery = true)
    SignUpUserScope afterDaySignUpUser();





    @Query(value = "" +
            "SELECT " +
            "    ... " +
            "FROM " +
            "    ( " +
            "        ... " +
            "    ) B LEFT OUTER JOIN ... C " +
            "                        ON ..." +
            "", nativeQuery = true)
    MeasUserScope totalMeasUser();
    
    @Query(value = "" +
            "SELECT " +
            "   ... " +
            "FROM " +
            "    ( " +
            "       ... " +
            "    ) B LEFT OUTER JOIN ... C " +
            "                        ON ..." +
            "", nativeQuery = true)
    MeasUserScope afterMonthMeasUser();

    @Query(value = "" +
            "SELECT " +
            "   ... " +
            "FROM " +
            "    ( " +
            "       ... " +
            "    ) B LEFT OUTER JOIN ... C " +
            "                        ON ..." +
            "", nativeQuery = true)
    MeasUserScope afterDayMeasUser();

    @Query(value = "" +
            "SELECT " +
            "   ... " +
            "FROM " +
            "    ( " +
            "       ... " +
            "    ) B LEFT OUTER JOIN ... C " +
            "                        ON ..." +
            "", nativeQuery = true)
    ManyMeasUser manyMeasUser(@Param("cnt") Integer cnt);

    @Query(value = "" +
            "SELECT " +
            "   ... " +
            "FROM " +
            "    ( " +
            "       ... " +
            "    ) B LEFT OUTER JOIN ... C " +
            "                        ON ..." +
            "", nativeQuery = true)
    List<MeasCrop> measCrops(@Param("day") Integer day);
}
