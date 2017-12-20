package com.visualkhh.cms.config;

import com.visualkhh.cms.config.security.WebSecurityConfigurerAdapter;
import com.visualkhh.common.config.CommonWebMvcConfigurerAdapter;
import com.visualkhh.common.config.properties.ProjectProperties;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
@Import({CommonWebMvcConfigurerAdapter.class})
@EnableWebMvc
@EnableConfigurationProperties(ProjectProperties.class)
@EnableScheduling
@EnableTransactionManagement
public class WebMvcConfigurerAdapter extends org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter {


//	@Autowired
//	private EntityManagerFactory entityManagerFactory;



//	@Bean
//	public SessionFactory getSessionFactory() {
//		if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
//			throw new NullPointerException("factory is not a hibernate factory");
//		}
//		return entityManagerFactory.unwrap(SessionFactory.class);
//	}
	@Bean(name = "sessionFactory") @Autowired
	public SessionFactory getSessionFactory(HibernateEntityManagerFactory g) {
		return g.getSessionFactory();
	}

	@Bean @Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(s);
		return transactionManager;
	}



////////////////////hibernate view //////////////////////////////////
	@Bean
	public FilterRegistrationBean openSessionInViewFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		OpenSessionInViewFilter filter = new OpenSessionInViewFilter();
		registrationBean.setFilter(filter);
		registrationBean.setOrder(5);
		return registrationBean;
	}
	@Bean
	public FilterRegistrationBean openEntityManagerInViewFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean(new OpenEntityManagerInViewFilter());
		OpenEntityManagerInViewFilter filter = new OpenEntityManagerInViewFilter();
		registrationBean.setFilter(filter);
		registrationBean.setOrder(5);
		return registrationBean;
	}
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
	}

	//리소스 패스 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**")	            .addResourceLocations("/assets/");
		registry.addResourceHandler("/*.map")	                .addResourceLocations("/");
		registry.addResourceHandler("/*.js")	                .addResourceLocations("/");
	}
}
