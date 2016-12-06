package com.khh.project.config;

import com.khh.Application;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;
import java.nio.charset.Charset;

@Configuration
@EnableScheduling
public class SpringBootServletInitializer extends org.springframework.boot.web.support.SpringBootServletInitializer {
    //war를 처리하기위해 필요하다.
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }


    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }


//////////////////////////scheduled  스케쥴 하기위하여//////////////////////
	//////////////@EnableScheduling 붙쳐줘야한다
	@Bean
	public ScheduledExecutorFactoryBean scheduledExecutorService() {
		ScheduledExecutorFactoryBean bean = new ScheduledExecutorFactoryBean();
		bean.setPoolSize(5);
		return bean;
	}


}