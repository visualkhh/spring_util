package com.khh.project.config.web;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

import javax.persistence.EntityManagerFactory;

//거의 여기서 config할것이 거의 없다. WebMvcConfigurerAdapter 에서 할것이 많다.
@Configuration
public class WebConfigurer {



}
