package com.khh.api.test.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
@Slf4j
public class RestErrorHandler extends DefaultResponseErrorHandler{ //implements ResponseErrorHandler  DefaultResponseErrorHandler

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return super.hasError(response);
	}
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		log.debug(""+response.getStatusCode());
		log.debug(""+response.getBody());
	}
}