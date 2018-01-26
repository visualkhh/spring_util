package com.khh.api.test.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omnicns.omnifit2.api.config.Omnifit2MediaType;
import com.omnicns.omnifit2.api.domain.UserDvc;
import com.omnicns.omnifit2.api.model.OmnifitHeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Slf4j
public class UserDvcUpdateTest {

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
		String omnifitHeaderStr = mapper.writeValueAsString(omnifitHeader);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(Omnifit2MediaType.OMNIFIT2_V1_JSON));
		headers.set("x-omnifit2", omnifitHeaderStr);
		return headers;
	}
	public static UserDvc body(){
		UserDvc body = new UserDvc();
		body.setAgeCd("A");
		body.setGenCd("M");
		return body;
	}


	public static void main(String[] args) throws JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());


		String uri = "http://localhost:8080/api/setting/user";
		HttpEntity entity = new HttpEntity(body(),header());

		restTemplate.setErrorHandler(new RestErrorHandler());
		ResponseEntity<String> r = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
		log.debug(r.getBody().toString());
	}
}
