package com.ceragem.iot.api.schedule;

import com.ceragem.iot.core.repository.CoreAdmRepository;
import com.ceragem.iot.core.service.CoreAdmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "schedule")
public class Scheduler {


    @Autowired
    CoreAdmRepository admRepository;

    @Autowired
    CoreAdmService admService;

	/*
	https://postitforhooney.tistory.com/entry/SpringScheduled-Cron-Example-%ED%91%9C%ED%98%84%EC%8B%9D

	초    |   분   |   시   |   일   |   월   |  요일  |   연도
	0~59 |   0~59 |   0~23|   1~31 | 1~12  |  0~6  | 생략가능

											(Sunday=0 or 7)
	 */

    //	이러면 30초마다 실행되는 것이다.
    //	@Scheduled(cron="*/30 * * * * *")

    // 매월요일 1시 10분 10초
    //	@Scheduled(cron = "1 * * * * *")
    //	@Scheduled(cron = "*/10 * * * * *")
//    @Scheduled(cron = "1 1 1 * * *")
//    public void finishAdmPtcpCdChange() {
//    }
}
