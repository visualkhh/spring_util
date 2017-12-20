package com.nhis.ggij.api.cms.config;

import com.nhis.ggij.api.core.config.CoreConfig;
import com.nhis.ggij.api.core.properties.HibernateProperties;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@Import({CoreConfig.class})
public class DataBaseConfig {
	private static final String DEFAULT_NAMING_STRATEGY = "org.springframework.boot.orm.jpa.hibernate.SpringNamingStrategy";

	@Value("${datasource.primary.jndi}")
	String primaryJndi;
	@Value("${datasource.inner.jndi}")
	String innerJndi;

	//http://millky.com/@origoni/post/1150?language=ko_kr
	////////////////////////1번째 DataSource
	@Bean
	//	@Bean
	@Primary
//	@ConfigurationProperties(prefix = "datasource.primary")
//	public DataSource primaryDataSource() {
//		return DataSourceBuilder.create().build();
//	}
	public DataSource primaryDataSource() throws NamingException {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		DataSource dataSource = dataSourceLookup.getDataSource("java:comp/env/"+primaryJndi);
		return dataSource;
//		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
//		bean.setJndiName("java:comp/env/"+primaryJndi);
////		bean.setJndiName(primaryJndi);
//		bean.setLookupOnStartup(false);
//		bean.setProxyInterface(DataSource.class);
//		bean.setResourceRef(true);
//		bean.afterPropertiesSet();
//		return (DataSource) bean.getObject();
	}


	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) throws NamingException {
		Map<String, String> propertiesHashMap = new HashMap<>();
		propertiesHashMap.put("hibernate.ejb.naming_strategy", DEFAULT_NAMING_STRATEGY);
		return builder.dataSource(primaryDataSource())
				.packages("com.nhis.ggij.api.cms.domain.primary")
				.properties(propertiesHashMap)
				.build();
	}

	@Primary
	@Bean(name = "transactionManager")
	PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) throws NamingException {
		return new JpaTransactionManager(entityManagerFactory(builder).getObject());
	}

	@Configuration
	@EnableJpaRepositories(
			basePackages = "com.nhis.ggij.api.cms.repository.primary",
			entityManagerFactoryRef = "entityManagerFactory",
			transactionManagerRef = "transactionManager"
	)
	static class DbArticleJpaRepositoriesConfig {
	}


	////////////////////////2번째 DataSource
	@Bean
//	@Bean
//	@ConfigurationProperties(prefix = "datasource.inner")
//	public DataSource innerDataSource() {
//		return DataSourceBuilder.create().build();
//	}
	public DataSource innerDataSource() throws NamingException {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		DataSource dataSource = dataSourceLookup.getDataSource("java:comp/env/"+innerJndi);
		return dataSource;
//		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
//		bean.setJndiName("java:comp/env/"+innerJndi);
//		bean.setLookupOnStartup(false);
//		bean.setProxyInterface(DataSource.class);
//		bean.setResourceRef(true);
//		bean.afterPropertiesSet();
//		return (DataSource) bean.getObject();
	}

	@Bean(name = "entityManagerFactoryUser")
	public LocalContainerEntityManagerFactoryBean userEntityManagerFactory(EntityManagerFactoryBuilder builder) throws NamingException {
		return builder.dataSource(innerDataSource())
				.packages("com.nhis.ggij.api.cms.domain.inner")
				.build();
	}
	@Bean(name = "transactionManagerUser")
	@Primary
	PlatformTransactionManager userTransactionManagerMain(EntityManagerFactoryBuilder builder) throws NamingException {
		return new JpaTransactionManager(userEntityManagerFactory(builder).getObject());
	}
	@Configuration
	@EnableJpaRepositories(
			basePackages="com.nhis.ggij.api.cms.repository.inner",
			entityManagerFactoryRef = "entityManagerFactoryUser",
			transactionManagerRef = "transactionManagerUser"
	)
	static class DbUserJpaRepositoriesConfig {
	}



	//////////////////hibernate
	@Autowired
	HibernateProperties hibernateProperties;
	//https://github.com/netgloo/spring-boot-samples/blob/master/spring-boot-mysql-hibernate/src/main/java/netgloo/configs/DatabaseConfig.java
	@Bean(name="primarySessionFactory")
	@Primary
	public LocalSessionFactoryBean primarySessionFactory() throws IOException, NamingException {
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		factory.setPackagesToScan();
		factory.setDataSource(primaryDataSource());
		if (hibernateProperties.getMappingLocations() != null) {
			ClassLoader cl = this.getClass().getClassLoader();
			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
			Resource[] resources = resolver.getResources(hibernateProperties.getMappingLocations()) ;
			for (Resource resource: resources){
				factory.setMappingLocations(resources);
			}
		}
		if (hibernateProperties.getPackagesToScan() != null) {
			factory.setPackagesToScan(hibernateProperties.getPackagesToScan());
		}else{
			factory.setPackagesToScan(CoreConfig.ROOT_PACKAGE);
		}
		if (hibernateProperties.getAnnotatedPackages() != null) {
			factory.setAnnotatedPackages(hibernateProperties.getAnnotatedPackages());
		}else{
			factory.setAnnotatedPackages(CoreConfig.ROOT_PACKAGE);
		}
		factory.setHibernateProperties(hibernateProperties.getProperties());
		Properties hibernateProperties = new Properties();
		SessionFactory sessionFactory = factory.getObject();
		return factory;
	}

	@Bean(name="innerSessionFactory")
	public LocalSessionFactoryBean userSessionFactory() throws IOException, NamingException {
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		factory.setPackagesToScan();
		factory.setDataSource(innerDataSource());
		if (hibernateProperties.getMappingLocations() != null) {
			ClassLoader cl = this.getClass().getClassLoader();
			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
			Resource[] resources = resolver.getResources(hibernateProperties.getMappingLocations()) ;
			for (Resource resource: resources){
				factory.setMappingLocations(resources);
			}
		}
		if (hibernateProperties.getPackagesToScan() != null) {
			factory.setPackagesToScan(hibernateProperties.getPackagesToScan());
		}else{
			factory.setPackagesToScan(CoreConfig.ROOT_PACKAGE);
		}
		if (hibernateProperties.getAnnotatedPackages() != null) {
			factory.setAnnotatedPackages(hibernateProperties.getAnnotatedPackages());
		}else{
			factory.setAnnotatedPackages(CoreConfig.ROOT_PACKAGE);
		}
		factory.setHibernateProperties(hibernateProperties.getProperties());
		Properties hibernateProperties = new Properties();
		SessionFactory sessionFactory = factory.getObject();
		return factory;
	}


}
