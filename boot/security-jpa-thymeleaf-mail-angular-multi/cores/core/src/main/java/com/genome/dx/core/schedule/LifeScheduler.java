package com.genome.dx.core.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LifeScheduler {

//
//    @Autowired
//    CoreCbcfService cbcfService;
//
//    @Value("${project.schedulers.LIFE_CHECK.cron}")
//    String cron;
//    @Value("${spring.application.name}")
//    String applicationName;
//
//
////	https://postitforhooney.tistory.com/entry/SpringScheduled-Cron-Example-%ED%91%9C%ED%98%84%EC%8B%9D
////	초    |   분   |   시   |   일   |   월   |  요일  |   연도
////	0~59 |   0~59 |   0~23|   1~31 | 1~12  |  0~6  | 생략가능
////											(Sunday=0 or 7)
//
//    @ApiOperation(value = CbcfProcessOperator.LIFE_CHECK)
//    @Scheduled(cron = "${project.schedulers." + CbcfProcessOperator.LIFE_CHECK + ".cron}")
//    public void lifeCheck() {
//        log.info("life check Scheduled " + applicationName);
//    }
}
