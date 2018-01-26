package com.khh.api.test.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omnicns.java.date.DateUtil;
import com.omnicns.omnifit2.api.config.Omnifit2MediaType;
import com.omnicns.omnifit2.api.model.OmnifitHeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Slf4j
public class BrdGetTest {

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
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//		headers.set("x-omnifit2", omnifitHeaderStr);
		return headers;
	}



	public static void main(String[] args) throws JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		MappingJackson2HttpMessageConverter jsonMessageConverter  = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper  = jsonMessageConverter.getObjectMapper();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		objectMapper.setDateFormat(dateFormat);
//		objectMapper.disable(
//				SerializationFeature.WRITE_DATES_AS_TIMESTAMPS //erializationFeature.WRITE_DATES_AS_TIMESTAMPS = yyyy-mm-dd’T’HH:mm:ssZZ
//		);
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//		restTemplate.getMessageConverters().add(jsonMessageConverter);
//		String jsonInString = "2018-01-11T22:14:07.30809:00";


//		DateUtil.getDate("yyyyMMddhhmmss",)
		Date f = DateUtil.modifyDate(Calendar.HOUR_OF_DAY,-2);
		Date t = new Date();


//		String uri = "http://localhost:8080/test/board";
		String uri = "http://localhost:8080/test/board1";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri)
				.queryParam("from", ZonedDateTime.ofInstant(f.toInstant(), ZoneId.systemDefault()))
				.queryParam("to", ZonedDateTime.ofInstant(t.toInstant(), ZoneId.systemDefault()));

		HttpEntity entity = new HttpEntity(header());

		restTemplate.getInterceptors().add(new LoggingRequestInterceptor());
		restTemplate.setErrorHandler(new RestErrorHandler());
		ResponseEntity<String> r = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, String.class);
//		log.debug(r.getBody().toString());
	}
}
