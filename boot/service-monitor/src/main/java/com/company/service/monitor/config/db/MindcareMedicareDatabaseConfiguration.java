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

@Configuration("mindcares_MediCareDatabaseConfiguration")
@EnableJpaRepositories(basePackages = "com.company.service.monitor.repository.mindcares.medicare", entityManagerFactoryRef = "mindcares_MediCareEntityManager", transactionManagerRef = "mindcares_MediCareTransactionManager")
public class MindcareMedicareDatabaseConfiguration {


    @Bean(value = "mindcares_MediCareHibernateProperties")
    @ConfigurationProperties("projects.mindcares.medicare.hibernate.property")
    public HashMap<String, Object> mindcares_KRHibernateProperties() {
        return new HashMap<String, Object>();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean mindcares_MediCareEntityManager() throws SQLException {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.company.service.monitor.domain.mindcares.medicare");
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = mindcares_KRHibernateProperties();
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public PlatformTransactionManager mindcares_MediCareTransactionManager() throws SQLException {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mindcares_MediCareEntityManager().getObject());
        return transactionManager;
    }

    @Bean(value = "mindcares_MediCareHikariConfig")
    @ConfigurationProperties(prefix="projects.mindcares.medicare.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean(value = "mindcares_MediCareDataSource")
    @ConfigurationProperties("projects.mindcares.medicare.datasource")
    public DataSource dataSource() throws SQLException {
        return new HikariDataSource(hikariConfig());
    }

}
