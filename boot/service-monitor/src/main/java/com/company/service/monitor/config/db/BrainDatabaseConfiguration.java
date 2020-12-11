package com.company.service.monitor.config.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;

@Configuration("brainDatabaseConfiguration")
@EnableJpaRepositories(basePackages = "com.company.service.monitor.repository.brain", entityManagerFactoryRef = "brainEntityManager", transactionManagerRef = "brainTransactionManager")
public class BrainDatabaseConfiguration {

    @Bean(value = "brainHibernateProperties")
    @ConfigurationProperties("projects.brain.hibernate.property")
    public HashMap<String, Object> brainHibernateProperties() {
        return new HashMap<String, Object>();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean brainEntityManager() throws SQLException {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.company.service.monitor.domain.brain");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = brainHibernateProperties();
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager brainTransactionManager() throws SQLException {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(brainEntityManager().getObject());
        return transactionManager;
    }

    @Bean(value = "brainHikariConfig")
    @ConfigurationProperties(prefix="projects.brain.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    //http://www.h2database.com/html/features.html
    @Bean(value = "brainDataSource")
    public DataSource dataSource() throws SQLException {
        return new HikariDataSource(hikariConfig());
    }


}
