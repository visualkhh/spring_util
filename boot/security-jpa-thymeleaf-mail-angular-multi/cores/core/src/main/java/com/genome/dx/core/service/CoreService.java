package com.genome.dx.core.service;

import com.genome.dx.core.config.properties.ProjectProperties;
import com.genome.dx.core.repository.BrdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CoreService {

    @Autowired
    ProjectProperties projectProperties;

    @Autowired
    BrdRepository cbcfRepository;

}
