package com.khh.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.khh.config.ConfigManager;
import com.omnicns.web.spring.application.ApplicationUtil;

//@Component
public class ConfigDataJob extends QuartzJobBean{
	Logger log = LoggerFactory.getLogger(this.getClass());
//	@Autowired 매번 생성되기떄문에 AutoWired가 되지 않는다  quartzJob
	ConfigManager configMng;
	//잡실행시 매번 생성된다
	public ConfigDataJob() {
//		log.info("ConfigDataJob constructor");
		configMng = ApplicationUtil.getWebApplicationContext().getBean(ConfigManager.class);
	}
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
//		long time = System.currentTimeMillis();
////		log.info("ConfigDataJob start" + time);
//		if(null!=configMng){
//			configMng.loadSetConfigFile();
//		}
//		log.info("ConfigDataJob end" + time);
//		log.info("ConfigDataJob data" + configMng.getParam("cbis_title"));
	}

}
