package com.nhis.ggij.api.cms.config;

import com.nhis.ggij.api.cms.CmsApiApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class SpringBootServletInitializer extends org.springframework.boot.web.support.SpringBootServletInitializer {
	//war
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CmsApiApplication.class);
	}
}
