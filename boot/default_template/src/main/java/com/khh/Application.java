package com.khh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Controller
//@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.khh.project"})
//@Configuration
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = {"com.khh.project"})
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}
