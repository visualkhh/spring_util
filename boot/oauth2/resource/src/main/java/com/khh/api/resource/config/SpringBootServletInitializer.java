package com.khh.api.resource.config;

import com.khh.api.ResourceApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class SpringBootServletInitializer extends org.springframework.boot.web.support.SpringBootServletInitializer {
	//war
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ResourceApplication.class);
	}
}
