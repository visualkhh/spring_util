package com.khh.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration

public class HibernateConfig {

//	@Value("${spring.hibernate.properties.hibernate.dialect}")
//	String hibernate_dialect;
//	@Value("${spring.hibernate.properties.hibernate.show_sql}")
//	String hibernate_show_sql;
//	@Value("${spring.hibernate.properties.hibernate.hbm2ddl.auto}")
//	String hibernate_hbm2ddl_auto;
//	@Value("${spring.hibernate.properties.hibernate.current_session_context_class}")
//	String hibernate_current_session_context_class;
//	@Autowired
//	DataSource dataSource;
//
//
//	@Bean
//	public LocalSessionFactoryBean sessionFactory() {
//		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
//		sessionFactoryBean.setDataSource(dataSource);
//		Properties hibernateProperties = new Properties();
//		hibernateProperties.put("hibernate.dialect", hibernate_dialect);
//		hibernateProperties.put("hibernate.show_sql", hibernate_show_sql);
//		hibernateProperties.put("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
//		hibernateProperties.put("hibernate.current_session_context_class", hibernate_current_session_context_class);
//		sessionFactoryBean.setHibernateProperties(hibernateProperties);
//		return sessionFactoryBean;
//	}
//
//	@Bean
//	public HibernateTransactionManager transactionManager() {
//		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//		transactionManager.setSessionFactory(sessionFactory().getObject());
//		return transactionManager;
//	}
//
//	@Bean
//	public FilterRegistrationBean registerOpenEntityManagerInViewFilterBean() {
//		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//		OpenSessionInViewFilter filter = new OpenSessionInViewFilter();
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
//	//hibernate sessionFactory사용하기위하여 아래 사용하였고
//	//properties쪽에   spring.jpa.properties."hibernate.current_session_context_class"=thread
//	// 사용할때에는  @Autowired public SessionFactory sessionFactory;
	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory() {
		return new HibernateJpaSessionFactoryBean();
	}
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
//	public FilterRegistrationBean registerOpenEntityManagerInViewFilterBean() {
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
