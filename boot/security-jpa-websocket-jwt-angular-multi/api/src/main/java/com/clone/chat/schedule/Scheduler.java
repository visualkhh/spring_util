package com.clone.chat.schedule;

import com.clone.chat.service.TestService;
import com.clone.chat.service.WebSocketManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j(topic = "schedule")
public class Scheduler {


    @Autowired
    TestService testService;
    @Autowired
    SimpMessagingTemplate template;


    @Autowired
    WebSocketManagerService webSocketManagerService;
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
//    @Scheduled(cron = "1 1 1 * * *")
    @Scheduled(cron = "*/2 * * * * *")
    public void finishAdmPtcpCdChange() {
//        Optional<Map.Entry<String, User>> user1 = webSocketManagerService.findEntreSetByUserId("user1");
//        if (user1.isPresent() && null != user1.get().getValue()) {
//            SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
//            User user = user1.get().getValue();
//            String sessionId = user1.get().getKey();
//            headerAccessor.setSessionId(sessionId);
//            headerAccessor.setLeaveMutable(true);
//            int value = (int) Math.round(Math.random() * 100d);
//            template.convertAndSendToUser(
//                    sessionId,
//                    "/queue/friends",
//                    friendRepository.findByUserId(user.getId()),
//                    headerAccessor.getMessageHeaders());
//
//        }

//        for (String listener : listeners) {
//            log.info("Sending notification to " + listener);
//            SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
//            headerAccessor.setSessionId(listener);
//            headerAccessor.setLeaveMutable(true);
//            int value = (int) Math.round(Math.random() * 100d);
//            template.convertAndSendToUser(
//                    listener,
//                    "/notification/item",
//                    new Notification(Integer.toString(value)),
//                    headerAccessor.getMessageHeaders());
//        }

        testService.chat(new Date());
    }
}
