package com.khh.api.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Slf4j
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
//@Order(Ordered.HIGHEST_PRECEDENCE)
//@Order(2)
public class WebSecurityConfigurerAdapter extends org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter {

	@Value("#{'${spring.h2.console.enabled:false}'}")
	boolean h2ConsoleEnabled;
	@Value("#{'${spring.h2.console.path:/h2-console}'}")
	String h2ConsolePath;
    @Override
    public void configure(WebSecurity web) throws Exception {
        if(h2ConsoleEnabled) {
            web.ignoring().antMatchers(h2ConsolePath+"/**");
        }
	}


}
