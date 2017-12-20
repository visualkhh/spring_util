package com.khh.api.config;

import com.khh.api.properties.ProjectProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.Filter;
import java.nio.charset.Charset;
import java.util.Locale;

//거의 여기서 config할것이 거의 없다. WebMvcConfigurerAdapter 에서 할것이 많다.
@Configuration
public class CoreConfig {
	@Value("#{'${spring.mvc.locale?:ko_KR}'}")
	Locale locale = null;
	@Value("#{'${spring.messages.basename?:classpath:/messages/message}'}")
	String messagesBasename = null;
	@Value("#{'${spring.messages.encoding?:UTF-8}'}")
	String messagesEncoding = null;
	@Value("#{'${spring.messages.cache-seconds?:-1}'}")
	int messagesCacheSeconds;
//	@Value("#{systemProperties['mongodb.port'] ?: 27017}")
//	private String mongodbPort;
//	@Value("#{config['mongodb.url'] ?: '127.0.0.1'}")
//	private String mongodbUrl;
//	@Value("#{aBean.age ?: 21}")
	@Autowired
	ProjectProperties projectProperties;

	//다국어 https://justinrodenbostel.com/2014/05/13/part-4-internationalization-in-spring-boot/
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(locale);
		return slr;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		if(null!=projectProperties && null!=projectProperties.getLocaleChange() && null!=projectProperties.getLocaleChange().getParamName()) {
			lci.setParamName(projectProperties.getLocaleChange().getParamName());
		}else{
			return null;
			//lci.setParamName("long");
		}
		return lci;
	}

	//message source
	@Bean
	public ReloadableResourceBundleMessageSource messageSource(){
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename(messagesBasename);                //"classpath:/messages/message"
		messageSource.setDefaultEncoding(messagesEncoding);
		messageSource.setCacheSeconds(messagesCacheSeconds);
		return messageSource;
	}

	@Bean
	public MessageSourceAccessor getMessageSourceAccessor(){
		ReloadableResourceBundleMessageSource m = messageSource();
		return new MessageSourceAccessor(m);
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
//	@Bean
//	public ScheduledExecutorFactoryBean scheduledExecutorService() {
//		ScheduledExecutorFactoryBean bean = new ScheduledExecutorFactoryBean();
//		bean.setPoolSize(5);
//		return bean;
//	}

}
