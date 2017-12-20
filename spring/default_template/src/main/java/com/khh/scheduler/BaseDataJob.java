package com.khh.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.khh.boot.BootManager;
import com.khh.boot.service.BootService;
import com.omnicns.web.spring.application.ApplicationUtil;

//@Component
public class BaseDataJob extends QuartzJobBean{
	Logger log = LoggerFactory.getLogger(this.getClass());
//	@Autowired 매번 생성되기떄문에 AutoWired가 되지 않는다  quartzJob
	BootManager bootMng;
//	@Autowired매번 생성되기떄문에 AutoWired가 되지 않는다  quartzJob
	BootService bootService;	
	//잡실행시 매번 생성된다
	public BaseDataJob() {
//		log.info("BaseDataJob constructor");
		bootMng = ApplicationUtil.getWebApplicationContext().getBean(BootManager.class);
		bootService = ApplicationUtil.getWebApplicationContext().getBean(BootService.class);
	}
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
//		long time = System.currentTimeMillis();
////		log.info("BaseDataJob start " + time);
//		if(null!=bootMng && null!=bootService){
//	    	bootMng.setRightGroup(bootService.getRightGroup());
//	    	bootMng.setMsgClass(bootService.getMsgClass());
//	    	bootMng.setArea(bootService.getArea());
//	    	bootMng.setMsgTemplate(bootService.getMsgTemplate());
//		}
//    	log.info("BaseDataJob end " + time);
    	
	}

}
