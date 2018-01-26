package com.khh.api.test.mock;

import com.omnicns.omnifit2.api.Omnifit2ApiApplication;
import com.omnicns.omnifit2.api.config.Omnifit2MediaType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Omnifit2ApiApplication.class)
@ActiveProfiles("local")
@Transactional
@Slf4j
public class Omnifit2ApplicationTests {


	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	@Before
	public void initMockMvc() {
		this.mockMvc = webAppContextSetup(wac).build();
	}


	@Test
	public void start() throws Exception {
		log.debug("------------");
//		RestTemplate client = RestTemplate.create();
		mockMvc.perform(get("/test/header-check/valid-serial-cpon").accept(Omnifit2MediaType.OMNIFIT2_V1_JSON_VALUE))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON))
		.andExpect(jsonPath("$.hello", is("world")));
	}

}
