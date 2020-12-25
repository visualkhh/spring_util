package com.ceragem.iot.core.service;

import com.ceragem.iot.core.config.properties.ProjectProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CoreService {

    @Autowired
    ProjectProperties projectProperties;

}
