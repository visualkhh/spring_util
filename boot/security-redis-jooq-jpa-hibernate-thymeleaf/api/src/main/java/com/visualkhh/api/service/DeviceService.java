package com.visualkhh.api.service;

import com.visualkhh.api.domain.DvcInfo;
import com.visualkhh.api.repository.DvcInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    @Autowired DvcInfoRepository dvcInfoRepository;

    public DvcInfo findBySerialNo(String serialNo) {
        DvcInfo dvcInfo = dvcInfoRepository.findFirstBySerialNo(serialNo);
        return dvcInfo;
    }

}
