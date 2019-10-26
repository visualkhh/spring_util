package com.omnicns.medicine.test.datajpa;

import com.omnicns.medicine.test.datajpa.domain.EventRcrtmtInfoTest;
import com.omnicns.medicine.MedicineApplication;
import com.omnicns.medicine.test.datajpa.repository.EventRcrtmtInfoTestRepository;
import com.omnicns.medicine.test.datajpa.repository.EventRcrtmtReviewTestRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MedicineApplication.class)
@ActiveProfiles("prod")
@Slf4j
@Transactional
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ImportAutoConfiguration({CodeService.class})
//@WebMvcTest(controllers = SearchController.class)
//@ContextConfiguration(classes = {FitBrainRepository.class, FitBrain.class})
//@TestPropertySource(properties = "spring.jpa.hibernate.use-new-id-generator-mappings=false")
//@ContextConfiguration(classes = {FitBrainRepository.class,CodeService.class,JCodeRepository.class})
//@DataJpaTest
//@JooqTest
public class EventTest {


	@Autowired
	EventRcrtmtInfoTestRepository eventRcrtmtInfoTestRepository;
	@Autowired
	EventRcrtmtReviewTestRepository eventRcrtmtReviewTestRepository;


	@Before
	public void initMockMvc() {
	}


	@Test
	public void 이벤트_조회() throws Exception {
//		List<EventRcrtmtTest> rcrtmt = eventRcrtmtTestRepository.findAll();
//		rcrtmt.forEach(it->{
//			log.info(it.getUserNm() + "\t" + it.getCpon() + "\t" + it.getBirdt() + "\t" + it.getEmail() + "\t" + it.getSerialNo());
//		});
//
//		log.info("--------------");

		List<EventRcrtmtInfoTest> info = eventRcrtmtInfoTestRepository.findAll();
		info.forEach(it->{
			log.info(it.getEventRcrtmtSeq() + "\t" + it.getUserNm() + "\t" + it.getCpon() + "\t" + it.getBirdt() + "\t" + it.getAddr() + "\t" + it.getRegDt() + "\t" + it.getSendInfo());
		});

//		log.info("--------------");
//
//		List<EventRcrtmtReviewTest> review = eventRcrtmtReviewTestRepository.findAll();
//		review.forEach(it->{
//			log.info(it.getUserNm() + "\t" + it.getCpon() + "\t" + it.getBirdt() + "\t" + it.getEmail()  + "\t" + it.getSnsUrl() + "\t" + it.getSnsUrl2() + "\t" + it.getSnsUrl3());
//		});
	}

}
