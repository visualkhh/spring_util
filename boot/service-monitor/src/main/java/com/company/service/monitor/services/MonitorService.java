package com.company.service.monitor.services;

import com.company.service.monitor.model.DayCount;
import com.company.service.monitor.model.DayCountGChartList;
import com.company.service.monitor.repository.brain.MsmtDtRstRepository;
import com.company.service.monitor.repository.mindcares.ceracheck.CeraCheckMeasureRelationshipRepositry;
import com.company.service.monitor.repository.mindcares.kr.MindcareKRMeasureRelationshipRepositry;
import com.company.service.monitor.repository.mindcares.medicare.MedicareMeasureRelationshipRepositry;
import com.company.service.monitor.repository.mindcares.seniorcare.SeniorcareMeasureRelationshipRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MonitorService {


    @Autowired
    MindcareKRMeasureRelationshipRepositry mindcareKRMeasureRelationshipRepositry;

    @Autowired
    MedicareMeasureRelationshipRepositry medicareMeasureRelationshipRepositry;

    @Autowired
    SeniorcareMeasureRelationshipRepositry seniorcareMeasureRelationshipRepositry;

    @Autowired
    CeraCheckMeasureRelationshipRepositry ceraCheckMeasureRelationshipRepositry;

    @Autowired
    MsmtDtRstRepository msmtDtRstRepository;

    public Map<String, List<DayCount>> getDayCount(Integer day) {
        Map<String, List<DayCount>> map = new HashMap<>();
        map.put("mindcare_krs", new DayCountGChartList(mindcareKRMeasureRelationshipRepositry.countByDay("ME001", day)));
        map.put("medicares", new DayCountGChartList(medicareMeasureRelationshipRepositry.countByDay("ME001", day)));
        map.put("seniorcares", new DayCountGChartList(seniorcareMeasureRelationshipRepositry.countByDay(day)));
        map.put("ceracheck_neuros", new DayCountGChartList(ceraCheckMeasureRelationshipRepositry.countByDay("ME001", day)));
        map.put("ceracheck_healths", new DayCountGChartList(ceraCheckMeasureRelationshipRepositry.countByDay("ME005", day)));
        map.put("brains", new DayCountGChartList(msmtDtRstRepository.countByDay(day)));
        return map;
    }

}
