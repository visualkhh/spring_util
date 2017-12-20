package com.khh.project.config;

import com.khh.Application;
import com.khh.project.config.properties.HibernateProperties;
import com.khh.project.config.properties.ProjectProperties;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.classpath.ClassPathRestartStrategy;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@Slf4j
public class HibernateConfig {

//	@Value("${spring.hibernate.properties.hibernate.dialect}")
//	String hibernate_dialect;
//	@Value("${spring.hibernate.properties.hibernate.show_sql}")
//	String hibernate_show_sql;
//	@Value("${spring.hibernate.properties.hibernate.hbm2ddl.auto}")
//	String hibernate_hbm2ddl_auto;
//	@Value("${spring.hibernate.properties.hibernate.current_session_context_class}")
//	String hibernate_current_session_context_class;
	@Autowired
	DataSource dataSource;

	@Autowired
	HibernateProperties hibernateProperties;
	@Autowired
	ProjectProperties ProjectProperties;


	@Autowired
	private ResourceLoader resourceLoader;
//
	//https://github.com/netgloo/spring-boot-samples/blob/master/spring-boot-mysql-hibernate/src/main/java/netgloo/configs/DatabaseConfig.java
	@Bean
	public LocalSessionFactoryBean sessionFactory() throws IOException {
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		factory.setPackagesToScan();
		factory.setDataSource(dataSource);
		if (hibernateProperties.getMappingLocations() != null) {
			//log.debug(System.getProperty("java.class.path"));
//			ClassPathResource resource = new ClassPathResource("resource/hibernate/mapper.hbm.xml");
//			BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
//			reader.lines().forEach(System.out::println);
//			Resource resource = new ClassPathResource(hibernateProperties.getMappingLocations());
//			ResourceLoader loader = new DefaultResourceLoader();
//			Resource resource = new FileSystemResource(hibernateProperties.getMappingLocations());
//			factory.setMappingLocations(classPathResource);
			ClassLoader cl = this.getClass().getClassLoader();
			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
			Resource[] resources = resolver.getResources(hibernateProperties.getMappingLocations()) ;
//			factory.setMappingLocations(resources);
			for (Resource resource: resources){
				factory.setMappingLocations(resources);
				//log.info(resource.getFilename());
			}
				//Resource resource = resourceLoader.getResource("classpath:hibernate/mappper.hbm.xml");
			//factory.setMappingLocations(resource);
		}
		if (hibernateProperties.getPackagesToScan() != null) {
			factory.setPackagesToScan(hibernateProperties.getPackagesToScan());
		}else{
			factory.setPackagesToScan(Application.BASE_PACKAGES);
		}
		if (hibernateProperties.getAnnotatedPackages() != null) {
			factory.setAnnotatedPackages(hibernateProperties.getAnnotatedPackages());
		}else{
			factory.setAnnotatedPackages(Application.BASE_PACKAGES);
		}
		factory.setHibernateProperties(hibernateProperties.getProperties());
		Properties hibernateProperties = new Properties();
//		factory.setMappingLocations(new FileSystemResource("D:/java/newWorkSpace/Baharan-Framework-web/target/framework-web-0.0.3-releases/WEB-INF/classes/hibernate/**/**.hbm.xml"));
//		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//		hibernateProperties.put("hibernate.show_sql", "true");
//		hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
//		hibernateProperties.put("hibernate.current_session_context_class", "thread");
//		factory.setHibernateProperties(hibernateProperties);
		SessionFactory sessionFactory = factory.getObject();
		return factory;
	}
//	@Bean
//	public HibernateTransactionManager transactionManager() {
//		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//		transactionManager.setSessionFactory(sessionFactory().getObject());
//		return transactionManager;
//	}
//
//	@Bean
//	public HibernateTemplate hibernateTemplate() throws Exception {
//		return new HibernateTemplate(sessionFactory().getObject());
//	}

	//기본 하이버네이트 사용할때 view까지 트랜젝션 및 open session을 이용하기위해..  근대 잘안된다...그지같다.
//	@Bean
//	public FilterRegistrationBean OpenSessionInViewFilter() {
//		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//		OpenSessionInViewFilter filter = new OpenSessionInViewFilter();
//		//filter.setSessionFactoryBeanName("LocalSessionFactoryBean");
//		registrationBean.setFilter(filter);
//		registrationBean.setOrder(5);
//		return registrationBean;
//	}










	////////////////////////hibernate////////////////////////////////////////////
	///hibernate transaction 을 위해
//	@Bean
//	public HibernateTransactionManager transactionManager() {
//		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//		transactionManager.setSessionFactory(sessionFactory().getObject());
//		return transactionManager;
//	}
//
	//hibernate sessionFactory사용하기위하여 아래 사용하였고
	//properties쪽에   spring.jpa.properties."hibernate.current_session_context_class"=thread
	// 사용할때에는  @Autowired public SessionFactory sessionFactory;
//	@Bean
//	public HibernateJpaSessionFactoryBean sessionFactory() {
//		HibernateJpaSessionFactoryBean h = new HibernateJpaSessionFactoryBean();
//		return h;
//	}
//	@Autowired
//	private EntityManagerFactory entityManagerFactory;
//	@Bean
//	public SessionFactory getSessionFactory() {
//		if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
//			throw new NullPointerException("factory is not a hibernate factory");
//		}
//		return entityManagerFactory.unwrap(SessionFactory.class);
//	}
//	@Bean
//	public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
//		return hemf.getSessionFactory();
//	}


//////////////////////hibernate view //////////////////////////////////
//	@Bean
//	public FilterRegistrationBean openSessionInViewFilter() {
//		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//		OpenSessionInViewFilter filter = new OpenSessionInViewFilter();
//		registrationBean.setFilter(filter);
//		registrationBean.setOrder(5);
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
/////////////////////////////////////////////////////////////////////


} // class DatabaseConfig
