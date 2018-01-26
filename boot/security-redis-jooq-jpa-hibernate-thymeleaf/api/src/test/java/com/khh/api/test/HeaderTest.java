package com.khh.api.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omnicns.java.jackson.JacksonUtil;
import com.omnicns.omnifit2.api.Omnifit2ApiApplication;
import com.omnicns.omnifit2.api.config.Omnifit2MediaType;
import com.omnicns.omnifit2.api.model.OmnifitHeader;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Omnifit2ApiApplication.class,webEnvironment = WebEnvironment.RANDOM_PORT)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
@Transactional
@Slf4j
public class HeaderTest {

	@Value("${project.properties.header-name}")
	String headerName = null;

	@Autowired
	private TestRestTemplate template;

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
		/* remove and reload test data */
//		customerRepository.deleteAll();
//		dataBuilder.createCustomers().forEach(customer -> customerRepository.save(customer));
	}

	@Test
	public void getAllCustomers() throws Exception {
//		ResponseEntity<String> response = template.getForEntity("/test/header-check/valid-serial-cpon", String.class);

		OmnifitHeader omnifitHeader = OmnifitHeader.builder().serialNo("serial").cponId("phoneNo").build();
		ObjectMapper mapper = new ObjectMapper();
		String omnifitHeaderStr = mapper.writeValueAsString(omnifitHeader);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", Omnifit2MediaType.OMNIFIT2_V1_JSON_VALUE);
		headers.add(headerName, omnifitHeaderStr);
		HttpEntity entity = new HttpEntity(headers);


		String uri = "/test/header-check/valid-serial-cpon";
		ResponseEntity<String> response = template.exchange(uri, HttpMethod.GET, entity, String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

//		List<Customer> customers = convertJsonToCustomers(response.getBody());
//		assertThat(customers.size(), equalTo(3));
	}

}
