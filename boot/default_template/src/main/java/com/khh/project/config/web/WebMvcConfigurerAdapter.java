package com.khh.project.config.web;

import com.khh.project.config.properties.ProjectProperties;
import com.khh.project.web.ErrorController;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;


@Configuration
@EnableWebMvc
@Slf4j
public class WebMvcConfigurerAdapter extends org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter {


	@Value("${spring.mvc.locale}")
	Locale locale = null;

	@Value("${spring.messages.basename}")
	String messagesBasename = null;
	@Value("${spring.messages.encoding}")
	String messagesEncoding = null;
	@Value("${spring.messages.cache-seconds}")
	int messagesCacheSeconds;

	@Autowired
	ProjectProperties projectProperties;

	@Autowired
	SessionFactory sessionFactory = null;

	//	preHandle 	boolean 	1. 클라이언트의 요청을 컨트롤러에 전달 하기 전에 호출
	//	 false 인 경우 intercepter  또는 controller 를 실행 시키지 않고 요청 종료
	//	postHandle 	void 	1. 컨트롤러 로직 실행 된 후 호출됨2. 컨트롤러 실행 도중 error 발생의 경우 postHandle() 는 실행	되지 않음
	//	 request 로 넘어온 데이터 가공시 많이 쓰임
	//	afterCompletion 	void 	1. 컨트롤러 로직 실행 된 후 호출 됨 2. 컨트롤러 실행 도중이나 view 페이지 실행 도중 error 발생 해도	실행됨
	//	 공통 Exception 처리 로직 작성시 많이 쓰임
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(csrfTokenAddingInterceptor()).addPathPatterns("/**");       //.includePathPatterns("/**") .excludePathPatterns("/**/*.ecxld");
		registry.addInterceptor(localeChangeInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(sessionFactoryTransctionInterceptor()).addPathPatterns("/**");
		//resource 서버 할때 세션쪽에 로그인된정보 Auhentication 안들어가는거때문에 인텁셉터 걸었다 하지만 그냥 웹이랑 ResourceServer랑 같이 둘수가 없다
//		registry.addInterceptor(securityContentHolderInterceptor()).addPathPatterns("/**");
	}

//	@Bean
//	public HandlerInterceptor securityContentHolderInterceptor() {
//		return new HandlerInterceptorAdapter() {
//			@Override
//			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//				Object auth = request.getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY+"_AUTHENTICATION");
//				if(null!=auth && null!=SecurityContextHolder.getContext()) {
//					SecurityContextHolder.getContext().setAuthentication((Authentication)auth);
//				}
//				return true;
//			}
//			@Override
//			public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//				Object auth = request.getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY+"_AUTHENTICATION");
//				if(null!=auth && null!=SecurityContextHolder.getContext()) {
//					SecurityContextHolder.getContext().setAuthentication((Authentication)auth);
//				}
//			}
//		};
//	}
	@Bean
	public HandlerInterceptor sessionFactoryTransctionInterceptor() {
		return new HandlerInterceptorAdapter() {
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
				sessionFactory.getCurrentSession().beginTransaction();
				return true;
			}
			@Override
			public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
				sessionFactory.getCurrentSession().getTransaction().commit();
			}
		};
	}
	@Bean
	public HandlerInterceptor csrfTokenAddingInterceptor() {
		return new HandlerInterceptorAdapter() {
			@Override
			public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView view) {
				CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
				if (token != null && null != view) {
					view.addObject(token.getParameterName(), token);
				}
			}
		};
	}



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
		lci.setParamName(projectProperties.getLocaleChange().getParamName());
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


    //로그인페이지.
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(WebSecurityConfigurerAdapter.LOGIN_PAGE).setViewName("security/login");
        registry.addViewController("/").setViewName("forward:/index.html");
//        registry.addViewController("/").setViewName("index.html");
//        registry.addViewController(WebSecurityConfigurerAdapter.LOGOUT_URL).setViewName("security/logout");
    }


//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        super.addArgumentResolvers(argumentResolvers);
//    }

    //    @Override
//    public void addInterceptors(final InterceptorRegistry resistry) {
//        resistry.addInterceptor(new LoginUserHandlerInterceptor()).addPathPatterns("/", "/*", "/**", "/*/**");
//    }

//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }

//    @Bean
//    public ServletRegistrationBean h2servletRegistration() {
//        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
//        registration.addUrlMappings("/console/*");
//        return registration;
//    }

//    @Bean
//    public HandlebarsViewResolver viewResolver() throws Exception {
//        HandlebarsViewResolver viewResolver = new HandlebarsViewResolver();
//        viewResolver.setOrder(1);
//        viewResolver.setPrefix("/WEB-INF/views/");
//        viewResolver.setCache(BooleanUtils.toBoolean(viewResolverCached));
//        viewResolver.registerHelpers(new HandlebarsHelper());
//        return viewResolver;
//    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return container -> {
            container.addErrorPages(
					new ErrorPage(HttpStatus.UNAUTHORIZED, 			ErrorController.PATH_ROOT + ErrorController.PATH_ERROR_401),
					new ErrorPage(HttpStatus.FORBIDDEN, 			ErrorController.PATH_ROOT + ErrorController.PATH_ERROR_403),
					new ErrorPage(HttpStatus.NOT_FOUND,				ErrorController.PATH_ROOT + ErrorController.PATH_ERROR_404),
					new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, ErrorController.PATH_ROOT + ErrorController.PATH_ERROR_500),
					new ErrorPage(Throwable.class, 					ErrorController.PATH_ROOT + ErrorController.PATH_ERROR_DEFAULT)
			);
        };
    }

//    @Bean
//    @ConfigurationProperties("spring.datasource")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }

	//리소스 패스 설정
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resource/**")	            .addResourceLocations("/resource/");
		registry.addResourceHandler("/static/**")	            .addResourceLocations("/static/");
		registry.addResourceHandler("/static/**")	            .addResourceLocations("/static/");
		registry.addResourceHandler("/img/**")		            .addResourceLocations("/img/");
		registry.addResourceHandler("/image/**")	            .addResourceLocations("/image/");
		registry.addResourceHandler("/assets/**")	            .addResourceLocations("/assets/");
		registry.addResourceHandler("/*.map")	                    .addResourceLocations("/");
		registry.addResourceHandler("/index.html")	            .addResourceLocations("/index.html");
//		registry.addResourceHandler("/0.chunk.js")	            .addResourceLocations("/0.chunk.js");
//		registry.addResourceHandler("/inline.bundle.js")	    .addResourceLocations("/inline.bundle.js");
//		registry.addResourceHandler("/main.bundle.js")	        .addResourceLocations("/main.bundle.js");
//		registry.addResourceHandler("/polyfills.bundle.js")	    .addResourceLocations("/polyfills.bundle.js");
//		registry.addResourceHandler("/styles.bundle.js")	    .addResourceLocations("/styles.bundle.js");
//		registry.addResourceHandler("/vendor.bundle.js")	    .addResourceLocations("/vendor.bundle.js");
		registry.addResourceHandler("/*.js")	                .addResourceLocations("/");
    }


}
