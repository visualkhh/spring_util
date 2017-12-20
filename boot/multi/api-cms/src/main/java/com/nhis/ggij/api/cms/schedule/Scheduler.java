package com.nhis.ggij.api.cms.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class Scheduler {

	// 매일 5시 30분 0초에 실행한다.
	@Scheduled(cron = "0 30 5 * * *")
	public void aJob() {
		// 실행될 로직
	}

	// 매월 1일 0시 0분 0초에 실행한다.
	@Scheduled(cron = "0 0 0 1 * *")
	public void anotherJob() {
		// 실행될 로직
	}

	@Scheduled(cron = "0/10 * * * * ?")
	public void anotherJob2() {
		log.debug(new Date().toString());
	}
}
