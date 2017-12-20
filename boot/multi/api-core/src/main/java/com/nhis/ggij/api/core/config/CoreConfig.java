package com.nhis.ggij.api.core.config;

//import HttpBodyEncryptFilter;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.nhis.ggij.api.core.filter.HttpBodyEncryptFilter;
import com.nhis.ggij.api.core.properties.HibernateProperties;
import com.nhis.ggij.api.core.properties.ProjectProperties;
import com.nhis.ggij.api.core.security.HttpBodyEncryptor;
import com.nhis.ggij.api.core.security.PrivacyEncryptor;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.util.Locale;

@Configuration
@EnableConfigurationProperties({ProjectProperties.class, HibernateProperties.class})
public class CoreConfig extends WebMvcConfigurerAdapter  {

	public static final String GROUP_PACKAGE	= "com.nhis.ggij";
	public static final String ROOT_PACKAGE		= "com.nhis.ggij.api.core";



	@Value("#{'${spring.mvc.locale?:ko_KR}'}")
	Locale locale = null;
	@Value("#{'${spring.messages.basename?:classpath:/messages/message}'}")
	String messagesBasename = null;
	@Value("#{'${spring.messages.encoding?:UTF-8}'}")
	String messagesEncoding = null;
	@Value("#{'${spring.messages.cache-seconds?:-1}'}")
	int messagesCacheSeconds;



	//암호화관련
	@Value("${encryp.http-body-key}")
	String httpBodyKey = null;
	@Value("${encryp.privacy-key}")
	String privacyKey = null;


//	@Autowired
//	private MessageSource messageSource;

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
//		MessageSource m = messageSource;
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


	@Bean
	public HttpBodyEncryptor httpBodyEncryptor() {
		return new HttpBodyEncryptor(httpBodyKey);
	}
	@Bean
	public PrivacyEncryptor privacyEncryptor() {
		return new PrivacyEncryptor(privacyKey);
	}

	////////filter
	@Bean
	public FilterRegistrationBean httpBodyEncryptFilterReg(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean(httpBodyEncryptFilter());
		registrationBean.addUrlPatterns("/*");
		registrationBean.setOrder(1);
		return registrationBean;
	}
	@Bean
	public HttpBodyEncryptFilter httpBodyEncryptFilter(){
		return new HttpBodyEncryptFilter();
	}

	//////servlet
//	@Bean
//	public ServletRegistrationBean h2servletRegistration(){
//		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
//		registrationBean.addUrlMappings("/console/*");
//		return registrationBean;
//	}

	/** Hibernate의 LazyLoading 회피 대응。  see JacksonAutoConfiguration */
	@Bean
	Hibernate5Module jsonHibernate5Module() {
		return new Hibernate5Module();
	}

	/** BeanValidation 메시지 UTF-8을 지원하는 Validator。 */
	@Bean
	LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
		factory.setValidationMessageSource(messageSource());
//		factory.setValidationMessageSource(messageSource);
		return factory;
	}

	/** 표준 Validator의 교환을 합니다。 */
	@Override
	public org.springframework.validation.Validator getValidator() {
		return validator();
	}

//////////////////////////scheduled  스케쥴 하기위하여//////////////////////
	//////////////@EnableScheduling 붙쳐줘야한다
//	@Bean
//	public ScheduledExecutorFactoryBean scheduledExecutorService() {
//		ScheduledExecutorFactoryBean bean = new ScheduledExecutorFactoryBean();
//		bean.setPoolSize(5);
//		return bean;
//	}



	////////////JNDI
	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatFactory() {


		return new TomcatEmbeddedServletContainerFactory() {
			@Override
			protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(Tomcat tomcat) {
				tomcat.enableNaming();
				return super.getTomcatEmbeddedServletContainer(tomcat);
			}
			@Override
			protected void postProcessContext(Context context) {
				ContextResource resource = new ContextResource();
				resource.setName("jdbc/primaryDB");
				resource.setType(DataSource.class.getName());
				resource.setProperty("driverClassName", "oracle.jdbc.OracleDriver");
				resource.setProperty("url", "jdbc:oracle:thin:@xxxx:1521:orcl");
				resource.setProperty("username", "xxxx");
				resource.setProperty("password", "xxxx");
				resource.setProperty("factory","org.apache.tomcat.jdbc.pool.DataSourceFactory");
				context.getNamingResources().addResource(resource);

				resource = new ContextResource();
				resource.setName("jdbc/innerDB");
				resource.setType(DataSource.class.getName());
				resource.setProperty("driverClassName", "oracle.jdbc.OracleDriver");
				resource.setProperty("url", "jdbc:oracle:thin:@xxxxxx:1521:orcl");
				resource.setProperty("username", "xxxx");
				resource.setProperty("password", "xxxx");
				resource.setProperty("factory","org.apache.tomcat.jdbc.pool.DataSourceFactory");
				context.getNamingResources().addResource(resource);
			}
		};

	}




//	@Bean(destroyMethod="")
//	public DataSource jndiDataSource() throws IllegalArgumentException, NamingException {
//		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
//		bean.setJndiName("java:comp/env/jdbc/myDataSource");
//		bean.setProxyInterface(DataSource.class);
//		bean.setLookupOnStartup(false);
//		bean.afterPropertiesSet();
//		return (DataSource)bean.getObject();
//	}
}
