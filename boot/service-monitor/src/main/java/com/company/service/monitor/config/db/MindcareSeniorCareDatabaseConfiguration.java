package com.company.service.monitor.config.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;

@Configuration("mindcares_SeniorCareDatabaseConfiguration")
@EnableJpaRepositories(basePackages = "com.company.service.monitor.repository.mindcares.seniorcare", entityManagerFactoryRef = "mindcares_SeniorCareEntityManager", transactionManagerRef = "mindcares_SeniorCareTransactionManager")
public class MindcareSeniorCareDatabaseConfiguration {


    @Bean(value = "mindcares_SeniorCareHibernateProperties")
    @ConfigurationProperties("projects.mindcares.seniorcare.hibernate.property")
    public HashMap<String, Object> mindcares_KRHibernateProperties() {
        return new HashMap<String, Object>();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean mindcares_SeniorCareEntityManager() throws SQLException {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.company.service.monitor.domain.mindcares.seniorcare");
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = mindcares_KRHibernateProperties();
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public PlatformTransactionManager mindcares_SeniorCareTransactionManager() throws SQLException {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mindcares_SeniorCareEntityManager().getObject());
        return transactionManager;
    }

    @Bean(value = "mindcares_SeniorCareHikariConfig")
    @ConfigurationProperties(prefix="projects.mindcares.seniorcare.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean(value = "mindcares_SeniorCareDataSource")
    @ConfigurationProperties("projects.mindcares.seniorcare.datasource")
    public DataSource dataSource() throws SQLException {
        return new HikariDataSource(hikariConfig());
    }

}
