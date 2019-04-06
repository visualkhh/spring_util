package com.omnicns.omnifit2.api.config.security;

import com.omnicns.omnifit2.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
//    private final UserService userDetailsService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.userDetailsService = userDetailsService;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().
//                authorizeRequests()
//                .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
//                .permitAll()
//                .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**")
//                .permitAll()
//                .anyRequest().authenticated().and()
//                .addFilter( new AuthenticationFilter(authenticationManager()) )
//                .addFilter( new AuthorizationFilter( authenticationManager() ))
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.headers().frameOptions().disable();
        http.authorizeRequests()
                .antMatchers("/", "/api/**").permitAll()
                .antMatchers("/swagger-ui.html").authenticated()//hasRole("AUTH")
                .and()
//                .formLogin()
//                .loginPage("/login")
//                .and()
//                .addFilter( new DefaultLoginPageGeneratingFilter())
                .httpBasic();
        //http.httpBasic().authenticationEntryPoint(basicAuthenticationPoint);
    }
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("swagger").password("1111!").roles("AUTH");
    }

}