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

@Configuration("mindcares_CeraCheckDatabaseConfiguration")
@EnableJpaRepositories(basePackages = "com.company.service.monitor.repository.mindcares.ceracheck", entityManagerFactoryRef = "mindcares_CeraCheckEntityManager", transactionManagerRef = "mindcares_CeraCheckTransactionManager")
public class MindcareCeraCheckDatabaseConfiguration {


    @Bean(value = "mindcares_CeraCheckHibernateProperties")
    @ConfigurationProperties("projects.mindcares.ceracheck.hibernate.property")
    public HashMap<String, Object> mindcares_CeraCheckHibernateProperties() {
        return new HashMap<String, Object>();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean mindcares_CeraCheckEntityManager() throws SQLException {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.company.service.monitor.domain.mindcares.ceracheck");
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = mindcares_CeraCheckHibernateProperties();
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public PlatformTransactionManager mindcares_CeraCheckTransactionManager() throws SQLException {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mindcares_CeraCheckEntityManager().getObject());
        return transactionManager;
    }

    @Bean(value = "mindcares_CeraCheckHikariConfig")
    @ConfigurationProperties(prefix="projects.mindcares.ceracheck.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean(value = "mindcares_CeraCheckDataSource")
    @ConfigurationProperties("projects.mindcares.ceracheck.datasource")
    public DataSource dataSource() throws SQLException {
        return new HikariDataSource(hikariConfig());
    }

}
