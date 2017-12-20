package com.khh;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.omnicns.java.db.hibernate.Hibernater;

@Configuration
public class CbisConfiguration {
	@Autowired
	public SessionFactory  sessionFactory;
	
	
//	@Bean(autowire=Autowire.NO)
	@Bean
	@Scope("prototype")
	public Hibernater getHibernater() {
		Hibernater hs = new Hibernater(sessionFactory);
		return hs;
	}


}
