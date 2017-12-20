package com.khh.api.resource.config;

import com.khh.api.config.ProjectConfig;
import com.khh.api.properties.HibernateProperties;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class DataBaseConfig {

	private static final String DEFAULT_NAMING_STRATEGY = "org.springframework.boot.orm.jpa.hibernate.SpringNamingStrategy";

	//http://millky.com/@origoni/post/1150?language=ko_kr
	////////////////////////1번째 DataSource
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "datasource.article")
	public DataSource articleDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
		Map<String, String> propertiesHashMap = new HashMap<>();
		propertiesHashMap.put("hibernate.ejb.naming_strategy", DEFAULT_NAMING_STRATEGY);

		return builder.dataSource(articleDataSource())
				.packages("com.khh.api.resource.domain.primary")
				.properties(propertiesHashMap)
				.build();
	}

	@Primary
	@Bean(name = "transactionManager")
	PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactory(builder).getObject());
	}

	@Configuration
	@EnableJpaRepositories(
			basePackages = "com.khh.api.resource.repository.primary",
			entityManagerFactoryRef = "entityManagerFactory",
			transactionManagerRef = "transactionManager"
	)
	static class DbArticleJpaRepositoriesConfig {
	}


	////////////////////////2번째 DataSource
	@Bean
	@ConfigurationProperties(prefix = "datasource.user")
	public DataSource userDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "entityManagerFactoryUser")
	public LocalContainerEntityManagerFactoryBean userEntityManagerFactory(EntityManagerFactoryBuilder builder) {

		return builder.dataSource(userDataSource())
				.packages("com.khh.api.resource.domain.inner")
				.build();
	}

	@Bean(name = "transactionManagerUser")
	@Primary
	PlatformTransactionManager userTransactionManagerMain(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(userEntityManagerFactory(builder).getObject());
	}

	@Configuration
	@EnableJpaRepositories(
			basePackages="com.khh.api.resource.repository.inner",
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
	public LocalSessionFactoryBean primarySessionFactory() throws IOException {
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		factory.setPackagesToScan();
		factory.setDataSource(articleDataSource());
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
			factory.setPackagesToScan(ProjectConfig.ROOT_PACKAGE);
		}
		if (hibernateProperties.getAnnotatedPackages() != null) {
			factory.setAnnotatedPackages(hibernateProperties.getAnnotatedPackages());
		}else{
			factory.setAnnotatedPackages(ProjectConfig.ROOT_PACKAGE);
		}
		factory.setHibernateProperties(hibernateProperties.getProperties());
		Properties hibernateProperties = new Properties();
		SessionFactory sessionFactory = factory.getObject();
		return factory;
	}

	@Bean(name="userSessionFactory")
	public LocalSessionFactoryBean userSessionFactory() throws IOException {
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		factory.setPackagesToScan();
		factory.setDataSource(userDataSource());
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
			factory.setPackagesToScan(ProjectConfig.ROOT_PACKAGE);
		}
		if (hibernateProperties.getAnnotatedPackages() != null) {
			factory.setAnnotatedPackages(hibernateProperties.getAnnotatedPackages());
		}else{
			factory.setAnnotatedPackages(ProjectConfig.ROOT_PACKAGE);
		}
		factory.setHibernateProperties(hibernateProperties.getProperties());
		Properties hibernateProperties = new Properties();
		SessionFactory sessionFactory = factory.getObject();
		return factory;
	}

}
