package com.today.house.config;

import com.today.house.config.properties.ProjectProperties;
import com.today.house.config.security.WebSecurityConfigurerAdapter;
import com.today.house.controller.AnonsController;
import com.today.house.filter.RequestLoggingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.persistence.EntityManagerFactory;
import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.util.Locale;

@Configuration
@Import({CommonWebMvcConfigurerAdapter.class})
@EnableWebMvc
@EnableConfigurationProperties(ProjectProperties.class)
@EnableScheduling
@EnableTransactionManagement
public class WebMvcConfigurerAdapter extends org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter {


	@Value("${project.properties.front-path}")
	private String frontPath;

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.KOREAN);
		return slr;
	}
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName(AnonsController.LANG_CHANGE_PARAM_NAME);
		return lci;
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor()).addPathPatterns(AnonsController.URI_PREFIX+ AnonsController.LANG_CHANGE_URI);
	}

	@Bean
	public RequestLoggingFilter requestLoggingFilter() {
		RequestLoggingFilter filter = new RequestLoggingFilter();
		return filter;
	}

	@Bean
	public FilterRegistrationBean commonsRequestLoggingFilter(RequestLoggingFilter filter) {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(filter);
		registrationBean.setOrder(5);
		return registrationBean;
	}


//	@Override
//	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//		argumentResolvers.add(new HandlerMethodArgumentResolver() {
//			@Override
//			public boolean supportsParameter(MethodParameter parameter) {
//				return MedicineHeader.class.isAssignableFrom(parameter.getParameterType());
//			}
//
//			@Override
//			public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//
//				String cponId 		= webRequest.getHeader("X-cponId"); // X-cponId	휴대폰 아이디
//				String serialNo 	= webRequest.getHeader("X-serialNo"); // X-serialNo	디바이스 고유 번호
//				String cponModel 	= webRequest.getHeader("X-cponModel"); // X-cponModel	PHONE 모델명
//				String osTypeCd 	= webRequest.getHeader("X-osTypeCd"); // X-osTypeCd	PHONE OS 정보
//				String osVer	 	= webRequest.getHeader("X-osVer"); // X-osVersion	PHONE OS VERSION
//				String pkgNm 		= webRequest.getHeader("X-pkgNm"); // X-pkgNm	어플 패키지명
//				String pkgVer	 	= webRequest.getHeader("X-pkgVer"); // X-pkgVersion	어플 VERSION
//				String userSeq 		= webRequest.getHeader("X-userSeq"); // X-userSeq	서버 회원 고유 아이디
//				MedicineHeader rval = null;
//
//				try {
//					rval = MedicineHeader.builder().cponId(cponId).cponModel(cponModel).serialNo(serialNo).osTypeCd(OsType.valueOf(osTypeCd)).osVer(osVer).pkgNm(pkgNm).pkgVer(pkgVer).userSeq(null!=userSeq?Integer.parseInt(userSeq):null).build();
//					if (parameter.hasParameterAnnotation(Valid.class) || parameter.hasParameterAnnotation(Validated.class)) {
//						WebDataBinder binder = binderFactory.createBinder(webRequest, rval, parameter.getParameterName());
//						this.validateIfApplicable(binder, parameter);
//						if (binder.getBindingResult().hasErrors()) {
//							throw new BindException(binder.getBindingResult());
//						}
//					}
//				} catch (BindException e) {
//					throw e;
//				} catch (Exception e) {
//					throw new ErrorMsgException(new Error(MsgCode.E20002), e);
//				}
//				return rval;
//			}
//
//			protected void validateIfApplicable(WebDataBinder binder, MethodParameter methodParam) {
//				Annotation[] annotations = methodParam.getParameterAnnotations();
//				for (Annotation ann : annotations) {
//					Validated validatedAnn = AnnotationUtils.getAnnotation(ann, Validated.class);
//					if (validatedAnn != null || ann.annotationType().getSimpleName().startsWith("Valid")) {
//						Object hints = (validatedAnn != null ? validatedAnn.value() : AnnotationUtils.getValue(ann));
//						Object[] validationHints = (hints instanceof Object[] ? (Object[]) hints : new Object[]{hints});
//						binder.validate(validationHints);
//						break;
//					}
//				}
//			}
//		});
//
//
////		PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
////		resolver.setFallbackPageable(new PageRequest(0, 520));
////		argumentResolvers.add(resolver);
//		PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver() {
//
//			@Override
//			public Pageable resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer,
//                                            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
//				Pageable p = super.resolveArgument(methodParameter, mavContainer, webRequest, binderFactory);
//				return getLimitsFromAnnotation(p, methodParameter);
//			}
//
//			private Pageable getLimitsFromAnnotation(Pageable p, MethodParameter methodParameter) {
//
//				PageableDefault limits = methodParameter.getParameterAnnotation(PageableDefault.class);
//
////				if (limits == null) return p;
////
////				if (p.getPageSize() > limits.size())
////					return new PageRequest(p.getPageNumber(), limits.size(), p.getSort());
////				else if (p.getPageSize() < limits.size())
////					return new PageRequest(p.getPageNumber(), limits.size(), p.getSort());
//
//				return p;
//			}
//		};
//
//		resolver.setMaxPageSize(Integer.MAX_VALUE);
//		argumentResolvers.add(resolver);
//		super.addArgumentResolvers(argumentResolvers);
//	}

//	@Bean
//	public SessionFactory getSessionFactory() {
//		if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
//			throw new NullPointerException("factory is not a hibernate factory");
//		}
//		return entityManagerFactory.unwrap(SessionFactory.class);
//	}




///////////////////////////////////////////////////////////////////

	//https://github.com/netgloo/spring-boot-samples/blob/master/spring-boot-mysql-hibernate/src/main/java/netgloo/configs/DatabaseConfig.java
//	@Bean
//	public LocalSessionFactoryBean sessionFactory() throws IOException {
//		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
//		factory.setPackagesToScan();
//		factory.setDataSource(dataSource);
//		if (hibernateProperties.getMappingLocations() != null) {
//			//log.debug(System.getProperty("java.class.path"));
////			ClassPathResource resource = new ClassPathResource("resource/hibernate/mapper.hbm.xml");
////			BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
////			reader.lines().forEach(System.out::println);
////			Resource resource = new ClassPathResource(hibernateProperties.getMappingLocations());
////			ResourceLoader loader = new DefaultResourceLoader();
////			Resource resource = new FileSystemResource(hibernateProperties.getMappingLocations());
////			factory.setMappingLocations(classPathResource);
//			ClassLoader cl = this.getClass().getClassLoader();
//			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
//			Resource[] resources = resolver.getResources(hibernateProperties.getMappingLocations()) ;
////			factory.setMappingLocations(resources);
//			for (Resource resource: resources){
//				factory.setMappingLocations(resources);
//				//log.info(resource.getFilename());
//			}
//			//Resource resource = resourceLoader.getResource("classpath:hibernate/mappper.hbm.xml");
//			//factory.setMappingLocations(resource);
//		}
//		if (hibernateProperties.getPackagesToScan() != null) {
//			factory.setPackagesToScan(hibernateProperties.getPackagesToScan());
//		}else{
//			factory.setPackagesToScan(Application.BASE_PACKAGES);
//		}
//		if (hibernateProperties.getAnnotatedPackages() != null) {
//			factory.setAnnotatedPackages(hibernateProperties.getAnnotatedPackages());
//		}else{
//			factory.setAnnotatedPackages(Application.BASE_PACKAGES);
//		}
//		factory.setHibernateProperties(hibernateProperties.getProperties());
//		Properties hibernateProperties = new Properties();
////		factory.setMappingLocations(new FileSystemResource("D:/java/newWorkSpace/Baharan-Framework-web/target/framework-web-0.0.3-releases/WEB-INF/classes/hibernate/**/**.hbm.xml"));
////		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
////		hibernateProperties.put("hibernate.show_sql", "true");
////		hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
////		hibernateProperties.put("hibernate.current_session_context_class", "thread");
////		factory.setHibernateProperties(hibernateProperties);
//		SessionFactory sessionFactory = factory.getObject();
//		return factory;
//	}
//	@Bean
//	public EmbeddedServletContainerCustomizer containerCustomizer() {
//		return container -> {
//			container.addErrorPages(
//					new ErrorPage(HttpStatus.BAD_REQUEST,           ErrorController.URI_PREFIX+ErrorController.ERROR_400),
//					new ErrorPage(HttpStatus.UNAUTHORIZED,          ErrorController.URI_PREFIX+ErrorController.ERROR_401),
//					new ErrorPage(HttpStatus.FORBIDDEN,             ErrorController.URI_PREFIX+ErrorController.ERROR_403),
//					new ErrorPage(AccessDeniedException.class,      ErrorController.URI_PREFIX+ErrorController.ERROR_403),
//					new ErrorPage(HttpStatus.NOT_FOUND,             ErrorController.URI_PREFIX+ErrorController.ERROR_404),
//					new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED,    ErrorController.URI_PREFIX+ErrorController.ERROR_405),
//					new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, ErrorController.URI_PREFIX+ErrorController.ERROR_500),
//					new ErrorPage(Throwable.class,                  ErrorController.URI_PREFIX+ErrorController.ERROR_DEFAULT)
//			);
//		};
//	}
	//로그인페이지.
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController(WebSecurityConfigurerAdapter.LOGIN_PAGE).setViewName(WebSecurityConfigurerAdapter.LOGIN_PAGE);
//		registry.addRedirectViewController("/swagger/v2/api-docs", "/v2/api-docs").setKeepQueryParams(true);
//		registry.addRedirectViewController("/swagger/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
//		registry.addRedirectViewController("/swagger/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
//		registry.addRedirectViewController("/swagger/swagger-resources", "/swagger-resources");
	}

	//리소스 패스 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")            .addResourceLocations("/WEB-INF/resources/");
		registry.addResourceHandler("/webjars/**")              .addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/swagger/**")          	.addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/assets/**")	            .addResourceLocations(frontPath +"/assets/");
		registry.addResourceHandler("/web-core-assets/**")	    .addResourceLocations(frontPath +"/web-core-assets/");
		registry.addResourceHandler("/favicon.ico")	            .addResourceLocations(frontPath +"/favicon.ico");
		registry.addResourceHandler("/*.html")	                .addResourceLocations(frontPath +"/");
		registry.addResourceHandler("/*.map")	                .addResourceLocations(frontPath +"/");
		registry.addResourceHandler("/*.js")	                .addResourceLocations(frontPath +"/");
//		registry.addResourceHandler(imageResourcePath+"/**")	.addResourceLocations("file:"+imageFilePath+"/");
//		registry.addResourceHandler(musicResourcePath+"/**")	.addResourceLocations("file:"+musicFilePath+"/");
//		registry.addResourceHandler(videoResourcePath+"/**")	.addResourceLocations("file:"+videoFilePath+"/");
//		registry.addResourceHandler(gameResourcePath+"/**")		.addResourceLocations("file:"+gameFilePath+"/");
//		registry.addResourceHandler(zipResourcePath+"/**")		.addResourceLocations("file:"+zipFilePath+"/");

	}

	@Bean
    MultipartConfigElement multipartConfigElement () {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		String location = System.getProperty("user.dir") + "/tmp";
		File tmpFile = new File(location);
		if (!tmpFile.exists()) {
			tmpFile.mkdirs();
		}
		factory.setLocation(location);
		return factory.createMultipartConfig();
	}




}
