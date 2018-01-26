package com.khh.api.test.datajpa;

import com.omnicns.omnifit2.api.Omnifit2ApiApplication;
import com.omnicns.omnifit2.api.repository.FitBrainRepository;
import com.omnicns.omnifit2.api.repository.JCodeRepository;
import com.omnicns.omnifit2.api.service.CodeService;
import com.omnicns.omnifit2.api.service.FitBrainService;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Omnifit2ApiApplication.class)
@ActiveProfiles("local")
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
public class FitBrain {


//	@Autowired
//	private WebApplicationContext wac;
//	private MockMvc mockMvc;
//	@Autowired
//	private TestEntityManager testEntityManager;

	@Autowired FitBrainService fitBrainService;

	@Before
	public void initMockMvc() {
	}


	@Test
	public void 두뇌컨텐츠_조회() throws Exception {
		Collection<com.omnicns.omnifit2.api.domain.FitBrain> data = fitBrainService.getList();
		log.debug("debug  {}",data);
//		log.debug("debug  1212121");
	}

}
