package com.company.service.monitor.services;

import com.company.service.monitor.repository.mindcares.kr.MindcareKRManualRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MindcareService {


    @Autowired
    MindcareKRManualRepositry mindcareManualRepositry;


    public Map<String, Object> getTotal() {
        Map<String, Object> map = new HashMap<>();
        map.put("kr_totalSignUpUser", mindcareManualRepositry.totalSignUpUser());
        map.put("kr_afterMonthSignUpUser", mindcareManualRepositry.afterMonthSignUpUser());
        map.put("kr_afterDaySignUpUser", mindcareManualRepositry.afterDaySignUpUser());
        map.put("kr_totalMeasUser", mindcareManualRepositry.totalMeasUser());
        map.put("kr_afterMonthMeasUser", mindcareManualRepositry.afterMonthMeasUser());
        map.put("kr_afterDayMeasUser", mindcareManualRepositry.afterDayMeasUser());
        map.put("kr_manyMeasUser", mindcareManualRepositry.manyMeasUser(3));
        map.put("kr_measCrops", mindcareManualRepositry.measCrops(7));
        return map;
    }


}
