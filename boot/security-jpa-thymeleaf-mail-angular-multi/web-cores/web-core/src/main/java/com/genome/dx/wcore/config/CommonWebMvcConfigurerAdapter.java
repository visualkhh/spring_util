package com.genome.dx.wcore.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.genome.dx.wcore.config.json.PageSerializer;
import com.omnicns.web.spring.message.CustomReloadableResourceBundleMessageSource;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;
import java.util.Map;

@Configuration
public class CommonWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
	@Value("${spring.messages.basename}")
	String messagesBasename = null;
	@Value("${spring.messages.encoding}")
	String messagesEncoding = null;
	@Value("${spring.messages.cache-duration}")
	int messagesCacheSeconds;

/*    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return container -> {
            container.addErrorPages(
                    new ErrorPage(HttpStatus.BAD_REQUEST,           ErrorController.URI_PREFIX+ErrorController.ERROR_400),
                    new ErrorPage(HttpStatus.UNAUTHORIZED,          ErrorController.URI_PREFIX+ErrorController.ERROR_401),
                    new ErrorPage(HttpStatus.FORBIDDEN,             ErrorController.URI_PREFIX+ErrorController.ERROR_403),
                    new ErrorPage(HttpStatus.NOT_FOUND,             ErrorController.URI_PREFIX+ErrorController.ERROR_404),
                    new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED,    ErrorController.URI_PREFIX+ErrorController.ERROR_405),
                    new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, ErrorController.URI_PREFIX+ErrorController.ERROR_500),
                    new ErrorPage(Throwable.class,                  ErrorController.URI_PREFIX+ErrorController.ERROR_DEFAULT)
            );
        };
    }
*/
	/**
	 * Make sure dates are serialised in
	 * ISO-8601 format instead as timestamps
	 */
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		for (HttpMessageConverter<?> converter : converters) {
			if (converter instanceof MappingJackson2HttpMessageConverter) {
				MappingJackson2HttpMessageConverter jsonMessageConverter  = (MappingJackson2HttpMessageConverter) converter;
				ObjectMapper objectMapper  = jsonMessageConverter.getObjectMapper();
//                objectMapper.setDateFormat(DateFormat."Y-m-d");
				objectMapper.disable(
						SerializationFeature.WRITE_DATES_AS_TIMESTAMPS //erializationFeature.WRITE_DATES_AS_TIMESTAMPS = yyyy-mm-dd’T’HH:mm:ssZZ
				);

				objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

				SimpleModule module = new SimpleModule("jackson-page-with-jsonview", Version.unknownVersion());
				module.addSerializer(PageImpl.class, new PageSerializer());
				objectMapper.registerModule(module);
//				objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, false);
//				objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
				break;
			}
		}
	}
	/**
	 * Default Error Message Custom
	 */
	@Bean
	public DefaultErrorAttributes defaultErrorAttributes() {
		return new DefaultErrorAttributes() {
			@Override
			public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
				Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
				errorAttributes.put("code", errorAttributes.get("status"));
//                Throwable throwable = getError(requestAttributes);
//                if (null!=throwable && throwable.getCause() != null) {
//                    Map<String, Object> causeErrorAttributes = new HashMap<>();
//                    causeErrorAttributes.put("exception", throwable.getCause().getClass().getName());
//                    causeErrorAttributes.put("message", throwable.getCause().getMessage());
//                    errorAttributes.put("cause", causeErrorAttributes);
//                }
				return errorAttributes;
			}
		};
	}


	/**
	 * scheduled  스케쥴 하기위하여 @EnableScheduling 붙쳐줘야한다
	 */
	@Bean
	public ScheduledExecutorFactoryBean scheduledExecutorService() {
		ScheduledExecutorFactoryBean bean = new ScheduledExecutorFactoryBean();
		bean.setPoolSize(5);
		return bean;
	}

	@Bean
	public TaskScheduler taskScheduler() {
		return new ConcurrentTaskScheduler();
	}
//	@Bean
//	public CustomPropertiesPersistor customPropertiesPersistor() {
//		return new CustomPropertiesPersistor();
//	}


	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {

//		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		CustomReloadableResourceBundleMessageSource messageSource = CustomReloadableResourceBundleMessageSource.getInstance();
//		messageSource.setPropertiesPersister(customPropertiesPersistor());
		messageSource.setBasename(messagesBasename);                //"classpath:/messages/message"
		messageSource.setDefaultEncoding(messagesEncoding);
		messageSource.setUseCodeAsDefaultMessage(false);
		messageSource.setCacheSeconds(messagesCacheSeconds);
		return messageSource;
	}

	//    public ResourceBundleMessageSource messageSource(){
	//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	//        messageSource.setBasename(messagesBasename);
	//        messageSource.setDefaultEncoding(messagesEncoding);
	//    }
//    @Bean
	@Bean
	public MessageSourceAccessor getMessageSourceAccessor() {
		MessageSource m = messageSource();
		return new MessageSourceAccessor(m);
	}

	/**
	 * BeanValidation 메시지 UTF-8을 지원하는 Validator。
	 */
	@Bean
    LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
		factory.setProviderClass(HibernateValidator.class);
		factory.setValidationMessageSource(messageSource());
		return factory;
	}

	/**
	 * 표준 Validator의 교환을 합니다。
	 */
	@Override
	public Validator getValidator() {
		return validator();
	}

	/** Hibernate의 LazyLoading 회피 대응。  see JacksonAutoConfiguration */
	@Bean
    Hibernate5Module jsonHibernate5Module() {
		return new Hibernate5Module();
	}

	@Bean
	public Module jacksonPageWithJsonViewModule() {
		SimpleModule module = new SimpleModule("jackson-page-with-jsonview", Version.unknownVersion());
//		module.addSerializer(PageImpl.class, new JacksonAdapter.JsonPageSerializer());
		module.addSerializer(PageImpl.class, new PageSerializer());
		return module;
	}



//	@Bean(name = "sessionFactory") @Autowired
//	public SessionFactory getSessionFactory(HibernateEntityManagerFactory g) {
//		return g.getSessionFactory();
//	}

//	@Bean
//    @Autowired
//	public HibernateTransactionManager transactionManager(SessionFactory s) {
//		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//		transactionManager.setSessionFactory(s);
//		return transactionManager;
//	}

	/////////hibernate view //////////////////////////////////
//	@Bean
//	public FilterRegistrationBean openSessionInViewFilter() {
//		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//		OpenSessionInViewFilter filter = new OpenSessionInViewFilter();
//		registrationBean.setFilter(filter);
////		registrationBean.addUrlPatterns("/*");
////		registrationBean.setOrder(Integer.MAX_VALUE);
//		registrationBean.setOrder(-900);
//		return registrationBean;
//	}
//	@Bean
//	public FilterRegistrationBean openEntityManagerInViewFilter() {
//		FilterRegistrationBean registrationBean = new FilterRegistrationBean(new OpenEntityManagerInViewFilter());
//		OpenEntityManagerInViewFilter filter = new OpenEntityManagerInViewFilter();
//		registrationBean.setFilter(filter);
//		registrationBean.setOrder(5);
//		return registrationBean;
//	}

    /*
     preHandle  boolean    1. 클라이언트의 요청을 컨트롤러에 전달 하기 전에 호출
      false 인 경우 intercepter  또는 controller 를 실행 시키지 않고 요청 종료
     postHandle     void   1. 컨트롤러 로직 실행 된 후 호출됨2. 컨트롤러 실행 도중 error 발생의 경우 postHandle() 는 실행  되지 않음
      request 로 넘어온 데이터 가공시 많이 쓰임
     afterCompletion    void   1. 컨트롤러 로직 실행 된 후 호출 됨 2. 컨트롤러 실행 도중이나 view 페이지 실행 도중 error 발생 해도  실행됨
      공통 Exception 처리 로직 작성시 많이 쓰임

    @Bean
    public HandlerInterceptor codeInterceptor() {
        return new HandlerInterceptorAdapter() {
            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
                super.postHandle(request, response, handler, modelAndView);
            }
        };
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(codeInterceptor()).addPathPatterns("/**");
    }
     */

}
