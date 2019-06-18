package com.lgu.cbc.core;

import com.lgu.cbc.common.LoopRunnable;
import com.lgu.cbc.core.config.CoreConfigration;
import com.lgu.cbc.core.config.properties.ProjectProperties;
import com.lgu.cbc.core.ha.HaChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CoreConfigration.class)
@Slf4j
public abstract class CbcApplicationBase implements LoopRunnable {
    @Autowired
    ProjectProperties projectProperties;
    @Autowired
    HaChecker haChecker;
    public void start(){

        log.info(projectProperties.getRunPollingTime()+"");
        log.info(projectProperties.getProperties()+"");
        haChecker.isActive();
        while (true) {
            this.loopRun();
            try {Thread.sleep(projectProperties.getRunPollingTime());} catch (Exception ex) { log.error("run loop",ex);}
        }
    }
}
