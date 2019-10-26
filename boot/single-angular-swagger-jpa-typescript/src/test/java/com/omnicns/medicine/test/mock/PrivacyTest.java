package com.omnicns.medicine.test.mock;

import com.omnicns.medicine.MedicineApplication;
import com.omnicns.medicine.config.security.WebSecurityConfigurerAdapter;
import com.omnicns.medicine.domain.convert.PrivateConvert;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.Convert;
import javax.servlet.Filter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MedicineApplication.class)
@ActiveProfiles("local")
@Slf4j
@Transactional
public class PrivacyTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	@Autowired
	private Filter springSecurityFilterChain;
	private MockHttpSession session;

	@Autowired
	PrivateConvert privateConvert;
	@Before
	public void initMockMvc() throws Exception {

	}

	@Test
	public void 개인정보_암복호화_테스트() throws Exception {

		log.debug(privateConvert.convertToDatabaseColumn("01011111111"));
		log.debug(privateConvert.convertToDatabaseColumn("01011111112"));
		log.debug(privateConvert.convertToDatabaseColumn("01011111113"));

	}


	@After
	public void after() throws Exception {
		//mockMvc.perform(logout(WebSecurityConfigurerAdapter.LOGOUT_URL)).andExpect(status().is3xxRedirection());
	}

}
