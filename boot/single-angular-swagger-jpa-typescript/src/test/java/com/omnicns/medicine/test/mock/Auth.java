package com.omnicns.medicine.test.mock;

import com.omnicns.medicine.MedicineApplication;
import com.omnicns.medicine.config.security.WebSecurityConfigurerAdapter;
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

import javax.servlet.Filter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MedicineApplication.class)
@ActiveProfiles("local")
@Transactional
public class Auth {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	@Autowired
	private Filter springSecurityFilterChain;
	private MockHttpSession session;

	@Before
	public void initMockMvc() throws Exception {
		mockMvc = webAppContextSetup(wac)
				.dispatchOptions(true)
				.addFilters(springSecurityFilterChain)
				.build();

		this.session = (MockHttpSession)mockMvc.perform(formLogin(WebSecurityConfigurerAdapter.LOGIN_PROCESSING_URL)
				.user("fwfw")
				.password("fwfw"))
				.andExpect(status().is3xxRedirection())
				.andReturn().getRequest().getSession();
	}

	@Test
	public void 로그인사용자_정보조회() throws Exception {

		mockMvc.perform(get("/auth/detail")
				.session(session)
				.with(csrf().asHeader()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.admNm").value("옴니핏"))
				.andDo(MockMvcResultHandlers.print());

	}


	@After
	public void after() throws Exception {
		//mockMvc.perform(logout(WebSecurityConfigurerAdapter.LOGOUT_URL)).andExpect(status().is3xxRedirection());
	}

}
