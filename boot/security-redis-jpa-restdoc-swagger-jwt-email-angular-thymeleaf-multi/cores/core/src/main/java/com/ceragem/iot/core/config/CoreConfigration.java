package com.ceragem.iot.core.config;

import com.ceragem.iot.core.config.properties.ProjectProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;

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

    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setProtocol("smtp");
        javaMailSender.setHost("127.0.0.1");
        javaMailSender.setPort(25);
        return javaMailSender;
    }

}
