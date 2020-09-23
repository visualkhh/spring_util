package com.genome.dx.core.config;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.genome.dx.core.config.properties.ProjectProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@Slf4j
@EnableConfigurationProperties(ProjectProperties.class)
@EnableScheduling
public class CoreConfigration {
//
//    @Autowired
//    ProjectProperties projectProperties;
//
//    /**
//     * Hibernate의 LazyLoading 회피 대응。  see JacksonAutoConfiguration
//     */
//    @Bean
//    Hibernate5Module jsonHibernate5Module() {
//        return new Hibernate5Module();
//    }
//
//    @Bean
//    @Profile("h2-local")
//    @ConfigurationProperties("spring.datasource.hikari")
//    public DataSource dataSource(
//            @Value("${spring.datasource.hikari.server-port:9092}") String port
//    ) throws SQLException {
//        org.h2.tools.Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", port).start();
//        return new com.zaxxer.hikari.HikariDataSource();
//    }


}
