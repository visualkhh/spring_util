package com.khh.api.test.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omnicns.omnifit2.api.Omnifit2ApiApplication;
import com.omnicns.omnifit2.api.config.Omnifit2MediaType;
import com.omnicns.omnifit2.api.domain.UserDvc;
import com.omnicns.omnifit2.api.model.OmnifitHeader;
import com.omnicns.omnifit2.common.domain.UserDvcBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Omnifit2ApiApplication.class)
@ActiveProfiles("local")
@Transactional
@Slf4j
public class Test {


	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Value("${project.properties.header-name}")
	String headerName = null;
	@Before
	public void initMockMvc() {
		this.mockMvc = webAppContextSetup(wac).build();
	}


	@org.junit.Test
	public void 테스트() throws Exception {

		HttpHeaders headers = new HttpHeaders();


		mockMvc.perform(get("/test").headers(headers).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).accept(Omnifit2MediaType.OMNIFIT2_V1_JSON_VALUE))
		.andExpect(status().isOk());
//		.andExpect(content().contentType(APPLICATION_JSON));
//		.andExpect(jsonPath("$.hello", is("world")));
	}

}
