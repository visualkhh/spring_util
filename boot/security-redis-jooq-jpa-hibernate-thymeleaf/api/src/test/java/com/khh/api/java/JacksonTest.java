package com.khh.api.java;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omnicns.java.jackson.JacksonUtil;
import com.omnicns.omnifit2.api.model.OmnifitHeader;

public class JacksonTest {
	public static void main(String[] args) throws JsonProcessingException {

		OmnifitHeader omnifitHeader = new OmnifitHeader();
		omnifitHeader.setSerialNo("gg");
		omnifitHeader.setCponId("agg");
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(omnifitHeader);

		System.out.printf(JacksonUtil.toJson(jsonInString));
	}
}
