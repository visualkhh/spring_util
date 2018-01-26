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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Omnifit2ApiApplication.class)
@ActiveProfiles("local")
@Slf4j
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@DataJpaTest
//@Transactional("transactionManager")
public class Code {


	@Autowired private DSLContext dsl;
	@Autowired CodeService codeService;
	//@Autowired FitBrainService fitBrainService;

	@Before
	public void initMockMvc() {
	}


	@Test
	public void 코드_조회() {
		List<com.omnicns.omnifit2.api.domain.Code> codes =  codeService.getCodes();
		log.debug("-----{}",codes);
	}
}
