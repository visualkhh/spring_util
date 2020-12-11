package com.company.service.monitor.config.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;


@Configuration("monitorDatabaseConfiguration")
@EnableJpaRepositories(basePackages = "com.company.service.monitor.repository.monitor", entityManagerFactoryRef = "monitorEntityManager", transactionManagerRef = "monitorTransactionManager")
public class MonitorDatabaseConfiguration {

    @Value("${projects.monitor.datasource.server.port:#{null}}") Optional<String> port;

    @Bean(value = "monitorHibernateProperties")
    @ConfigurationProperties("projects.monitor.hibernate.property")
    public HashMap<String, Object> monitorHibernateProperties() {
        return new HashMap<String, Object>();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean monitorEntityManager() throws SQLException {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.company.service.monitor.domain.monitor");
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = monitorHibernateProperties();
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Primary
    @Bean
    public PlatformTransactionManager monitorTransactionManager() throws SQLException {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(monitorEntityManager().getObject());
        return transactionManager;
    }

    @Bean(value = "monitorHikariConfig")
    @ConfigurationProperties(prefix="projects.monitor.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Primary
    @Bean(value = "monitorDataSource")

    public DataSource dataSource() throws SQLException {
        return new HikariDataSource(hikariConfig());
    }
}
