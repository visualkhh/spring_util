package com.khh.multidatabases.config.d1;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
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

@Configuration("d1-DatabaseConfiguration")
@EnableJpaRepositories(basePackages = "com.khh.multidatabases.repository.d1", entityManagerFactoryRef = "d1EntityManager", transactionManagerRef = "d1TransactionManager")
public class DatabaseConfiguration {


    @Bean(value = "d1HibernateProperties")
    @ConfigurationProperties("projects.d1.hibernate.property")
    public HashMap<String, Object> d1HibernateProperties() {
        return new HashMap<String, Object>();
//        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
//        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
//        return properties;
    }
    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean d1EntityManager() throws SQLException {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.khh.multidatabases.domain.d1");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = d1HibernateProperties();
        em.setJpaPropertyMap(properties);

        return em;
    }
    @Primary
    @Bean
    public PlatformTransactionManager d1TransactionManager() throws SQLException {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(d1EntityManager().getObject());
        return transactionManager;
    }

    // projects
//    @Bean("d1DataSource")
//    @ConfigurationProperties("projects.d1.datasource") // yml의 설정값을 Set한다.
//    public DataSource d1DataSource() throws SQLException {
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }

    @Bean(value = "d1HikariConfig")
    @ConfigurationProperties(prefix="projects.d1.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    //http://www.h2database.com/html/features.html
    @Primary
    @Bean(value = "d1DataSource")
//    @ConfigurationProperties("projects.d1.datasource")
    public DataSource dataSource() throws SQLException {
//    public DataSource d1DataSource(@Value("${projects.d1.datasource.server.port:#{null}}") Optional<String> port) throws SQLException {
//        if(port.isPresent()){
//                Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", port.get()).start();
//        };
//        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        return dataSource;
//        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("jdbc.driverClassName")));
//        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("user.jdbc.url")));
//        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("jdbc.user")));
//        dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("jdbc.pass")));
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
        return new HikariDataSource(hikariConfig());
    }

//    @Bean(value = "d2DataSource")
//    @ConfigurationProperties("projects.d2.datasource")
////    public DataSource d2DataSource(@Value("${projects.d2.datasource.server.port:#{null}}") Optional<String> port) throws SQLException {
//    public DataSource d2DataSource() throws SQLException {
////        if(port.isPresent()){
////            Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", port.get()).start();
////        };
//        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        return dataSource;
//    }

//    @Profile("h2-local")
//    @Bean
//    @ConfigurationProperties("spring.datasource.hikari")
//    public DataSource dataSource(
////            @Value("${spring.datasource.hikari.jdbc-url}") String jdbcUrl
//            @Value("${spring.datasource.hikari.server-port:9092}") String port
//    ) throws SQLException {
//        // org.h2.tools.Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", jdbcUrl.split("tcp://localhost:")[1].split("/")[0]).start();
//        org.h2.tools.Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", port).start();
//        return new com.zaxxer.hikari.HikariDataSource();
//    }


}
