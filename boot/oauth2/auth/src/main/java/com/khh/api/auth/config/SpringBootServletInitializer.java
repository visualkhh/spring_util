package com.khh.api.auth.config;

import com.khh.api.AuthApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class SpringBootServletInitializer extends org.springframework.boot.web.support.SpringBootServletInitializer {
	//war
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AuthApplication.class);
	}
}
