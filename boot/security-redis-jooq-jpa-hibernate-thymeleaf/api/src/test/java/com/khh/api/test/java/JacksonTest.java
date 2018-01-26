package com.khh.api.test.java;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omnicns.java.jackson.JacksonUtil;
import com.khh.api.test.java.model.JTest;

import java.io.IOException;

public class JacksonTest {
	public static void main(String[] args) throws IOException {

//		OmnifitHeader omnifitHeader = new OmnifitHeader();
//		omnifitHeader.setSerialNo("gg");
//		omnifitHeader.setCponId("agg");
		ObjectMapper mapper = new ObjectMapper();
//
//		JTest data = new JTest();
//		data.setName("gg");
//		data.setTermsTypeCode(TermsTypeCode.TMT002);
//		String json = mapper.writeValueAsString(data);
//		System.out.println(json);



		JTest data2 = JacksonUtil.toObject("{\"termsTypeCode\":\"TMT002\",\"name\":\"gg\"}",JTest.class);
		System.out.println(data2.toString());


		//it
//		System.out.printf(JacksonUtil.toJson(jsonInString));
	}
}
