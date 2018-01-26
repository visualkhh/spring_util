package com.khh.api.test.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omnicns.omnifit2.api.Omnifit2ApiApplication;
import com.omnicns.omnifit2.api.config.Omnifit2MediaType;
import com.omnicns.omnifit2.api.domain.UserDvc;
import com.omnicns.omnifit2.api.model.OmnifitHeader;
import com.omnicns.omnifit2.common.domain.UserDvcBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Omnifit2ApiApplication.class,webEnvironment = WebEnvironment.RANDOM_PORT)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
//@TransactionConfiguration(defaultRollback = false)
@Rollback(true)
@Transactional
@Slf4j
public class TestRestTemplate {

	@Value("${project.properties.header-name}")
	String headerName = null;

	@LocalServerPort
	private int port;

	@Autowired
	private org.springframework.boot.test.web.client.TestRestTemplate template;

//	@Autowired
//	private DataBuilder dataBuilder;



	/*http://blog.saltfactory.net/using-resttemplate-in-spring/
		restTemplate.getForObject() : 기본 Http Header를 사용며 결과를 객체로 반환 받는다.
		restTemplate.getForEntity() : 기본 Http Header를 사용하며 결과를 Http ResponseEntity로 반환 받는다.
		restTemplate.exchange() : Http Header 를 수정할 수 있고 결과를 Http ResponseEntity로 반환 받는다.
		restTemplate.execute() : Request/Response 콜백을 수정할 수 있다.
	 */
	@Before
	public void setUp() throws Exception {
		template.getRestTemplate().getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		/* remove and reload test data */
//		customerRepository.deleteAll();
//		dataBuilder.createCustomers().forEach(customer -> customerRepository.save(customer));
	}



//	@Rollback(true)
	@Test
	@Transactional
	public void 회원정보_수정() throws Exception {
//		ResponseEntity<String> response = template.getForEntity("/test/header-check/valid-serial-cpon", String.class);

		OmnifitHeader omnifitHeader = OmnifitHeader.builder().serialNo("serial").cponId("phoneNo").userDvcSeq(198).build();
		String omnifitHeaderStr = new ObjectMapper().writeValueAsString(omnifitHeader);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", Omnifit2MediaType.OMNIFIT2_V1_JSON_VALUE);
		headers.add(headerName, omnifitHeaderStr);

		UserDvcBase body = UserDvc.builder().genCd("GEC002").build();
		HttpEntity entity = new HttpEntity(body, headers);



		String uri = "/api/user";
		ResponseEntity<String> response = template.exchange(uri, HttpMethod.PUT, entity, String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

//		List<Customer> customers = convertJsonToCustomers(response.getBody());
//		assertThat(customers.size(), equalTo(3));
	}




}
