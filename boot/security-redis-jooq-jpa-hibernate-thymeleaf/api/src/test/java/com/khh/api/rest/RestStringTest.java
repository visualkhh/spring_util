package com.khh.api.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omnicns.omnifit2.api.config.Omnifit2MediaType;
import com.omnicns.omnifit2.api.domain.UserDvc;
import com.omnicns.omnifit2.api.model.OmnifitHeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Slf4j
public class RestStringTest {

	/*
	http://blog.saltfactory.net/using-resttemplate-in-spring/
    restTemplate.getForObject() : 기본 Http Header를 사용며 결과를 객체로 반환 받는다.
    restTemplate.getForEntity() : 기본 Http Header를 사용하며 결과를 Http ResponseEntity로 반환 받는다.
    restTemplate.exchange() : Http Header 를 수정할 수 있고 결과를 Http ResponseEntity로 반환 받는다.
    restTemplate.execute() : Request/Response 콜백을 수정할 수 있다.
 */


	public static HttpHeaders header() throws JsonProcessingException {
		OmnifitHeader omnifitHeader = OmnifitHeader.builder().userDvcSeq(null).serialNo("SEO_SERIAL").cponId("phoneNo").build();
		ObjectMapper mapper = new ObjectMapper();
		String omnifitHeaderStr = "{\"cccc\":\"zz\", \"cponId\":\"e7f295f1-7a59-40f8-b5cd-8dbfbac48f22\",\"os\":\"OST001\",\"pkgNm\":\"com.omnicns.omnifit.omnifit_2nd_gen\",\"pkgVersion\":\"0.0.1\",\"cponModel\":\"LG-F240K\",\"serialNo\":\"1234\",\"userDvcSeq\":\"\"}";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(Omnifit2MediaType.OMNIFIT2_V1_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("x-omnifit2", omnifitHeaderStr);
		return headers;
	}
	public static String body(){
		return "{'regDt':''}";
	}


	public static void main(String[] args) throws JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

/*
	API 도메인: bgodev.omnifit.co.kr
PATH : /home/omnifit2/bgo/webapps/api
	CMS 도메인: bgocmsdev.omnifit.co.kr
PATH : /home/omnifit2/bgo/webapps/cms

 */

//		String uri = "http://localhost:8080/test/date";
		String uri = "http://localhost:8080/test";
		uri+="/header-check/valid-serial-cpon";


//		String uri = "http://bgodev.omnifit.co.kr/api/fitbrain/history";
		HttpEntity entity = new HttpEntity(body(), header());

//		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
//		interceptors.add(new LoggingRequestInterceptor());
//		restTemplate.setInterceptors(interceptors);
		restTemplate.setErrorHandler(new RestErrorHandler());
//		restTemplate.getInterceptors().add(new LoggingRequestInterceptor());
//		ResponseEntity<String> r = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
		ResponseEntity<String> r = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
		log.debug("statusCode :" + r.getStatusCode());
		log.debug("headers :" + r.getHeaders());
		log.debug(r.getBody().toString());
	}
}
