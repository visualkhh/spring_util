package com.genome.dx.gmp.schedule;

import com.genome.dx.core.code.UseCd;
import com.genome.dx.core.domain.Adm;
import com.genome.dx.core.repository.AdmRepository;
import com.genome.dx.core.service.AdmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j(topic = "schedule")
public class Scheduler {


	@Autowired
	AdmRepository admRepository;

	@Autowired
	AdmService admService;

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



	@Scheduled(cron = "1 1 1 * * *")
	public void finishAdmPtcpCdChange() {
		List<Adm> finishUser = admService.findByFinishAdm();
		finishUser.stream().forEach(it -> {
			log.debug("finish Adm -> {}", it);
			it.setUseCd(UseCd.USE002);
			admService.save(it);
		});
	}

//	@Scheduled(cron = "10 10 1 * * 1")
//	public void sendConformityEmails() {
//		// 실행될 로직
//        log.info("sendConformityEmails start");
//		List<User> validUsers = userService.findValidPtcpDt();t
//		log.info("sendConformityEmails userList => " + validUsers.toString());
//
//		//1주전 데이터를 순응도 메일보낸다.
//		validUsers.stream().forEach(user -> {
//			log.info("sendConformityEmails userList => at -> " + user);
//			//일주일 구하기.
//			ZonedDateTime startWeekDateTime = ZonedDateTime.now().minusWeeks(1).with(LocalTime.of(0, 0, 0, 0));
//			ZonedDateTime startDateTime = startWeekDateTime.minusDays(startWeekDateTime.getDayOfWeek().getValue() - 1); //월요일
//			ZonedDateTime endDateTime = startDateTime.plusDays(6); //일요일
//			GregorianCalendar calendar = GregorianCalendar.from(endDateTime);
//			GameSetWeekRst emailData = new GameSetWeekRst(startDateTime, endDateTime, Integer.parseInt(endDateTime.getYear() + "" +calendar.get(Calendar.WEEK_OF_YEAR)), ConformityCd.CFT002);
//			for (GameSetWeekRst setWeekRst : CollectionUtils.emptyIfNull(user.getGameSetWeekRsts())){
//				long now = startDateTime.toInstant().toEpochMilli();
//				if(null != setWeekRst && null != setWeekRst.getStartDt() && null != setWeekRst.getEndDt() && now>=setWeekRst.getStartDt().toInstant().toEpochMilli() && now<=setWeekRst.getEndDt().toInstant().toEpochMilli()) {
//					emailData = setWeekRst;
//					continue;
//				}
//			}
//
//			if (null != emailData) {
//				try {
//					log.info("sendConformityEmails userList => at -> email -> " + user.toString()+ ","+ emailData.toString());
//					clinicalService.sendConformityEmails(user, emailData);
//				} catch (Exception e) {
//					log.error("Scheduled sendConformityEmails ERROR", e);
//				}
//			}
//		});
//	}
//
//
////	@Scheduled(cron = "0 */30 * * * *")
//	@Scheduled(cron = "10 20 1 * * 1")
////	@Scheduled(cron = "10 11 11 * * 1")
//	public void sendAdminConformityEmails() {
//		// 실행될 로직
//
//        // log.info("schedule start ------------------------->>>>>");
//		// 1. 사용중이고 이메일 주소가 있는 상담사 정보를 받는다.
//		List<Adm> validAdms = adminService.getAdminInfo();
//
//		// 2. 상담사의 업체코드와 동일한 사용자의 일주일 정보를 받아서 화면을 그린다.
//		validAdms.stream().forEach(adm -> {
//
//			List<User> userData = userService.findUserInfo(adm.getAfftCd());
//            // log.info("sendConformityEmails adm.getAdmNm  :   "+ adm.getAdmNm());
//			try {
//				clinicalAdminService.sendConformityEmailsForAdmin(adm, userData);
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			} catch (MessagingException e) {
//				e.printStackTrace();
//			}
//		});
//
//	}
}
