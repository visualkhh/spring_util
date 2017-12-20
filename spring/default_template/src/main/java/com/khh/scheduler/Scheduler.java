package com.khh.scheduler;

import com.khh.boot.BootManager;
import com.khh.boot.service.BootService;
import com.khh.config.ConfigManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Scheduler {
//	@Autowired
	BootManager bootMng = BootManager.getInstance();
	@Autowired
	BootService bootService;
//	@Autowired
	ConfigManager configMng = ConfigManager.getInstance();
	public Scheduler() {
	}

	//5분마다 (55-5 분 사이는 피한다)
	@Scheduled(cron = "0 5,10,15,20,25,30,35,40,45,50,55 * * * *")
	public void baseDataJob() {
		long time = System.currentTimeMillis();
		log.info("BaseDataJob start " + time);
		if(null!=bootMng && null!=bootService){
			bootMng.setRightGroup(bootService.getRightGroup());
			bootMng.setMsgClass(bootService.getMsgClass());
			bootMng.setArea(bootService.getArea());
			bootMng.setMsgTemplate(bootService.getMsgTemplate());
		}
	}

	//10초마다
	@Scheduled(cron = "10,20,30,40,50 * * * * *")
	public void configDataJob() {
		long time = System.currentTimeMillis();
		log.info("ConfigDataJob start " + time);
		if(null!=configMng){
			configMng.loadSetConfigFile();
		}
	}

}
