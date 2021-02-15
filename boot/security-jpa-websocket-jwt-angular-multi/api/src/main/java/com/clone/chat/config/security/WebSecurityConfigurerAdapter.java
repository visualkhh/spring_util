package com.clone.chat.config.security;


import com.clone.chat.code.UserRole;
import com.clone.chat.config.security.auth.ApiUserDetailsService;
import com.clone.chat.config.security.jwt.JwtConfig;
import com.clone.chat.config.security.jwt.JwtTokenVerifier;
import com.clone.chat.config.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.clone.chat.repository.UserRepository;
import com.clone.chat.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfigurerAdapter extends org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TokenService tokenService;


	@Autowired
	ApiUserDetailsService userDetailsService;

	@Autowired
	JwtConfig jwtConfig;

	@Autowired
	ObjectMapper objectMapper;

	public static final String SECURITY_PATH                = "/securitys";
	public static final String LOGOUT_URL                   = SECURITY_PATH+"/sign-out";
	public static final String LOGIN_PAGE                   = "/#/login";
	public static final String LOGIN_PROCESSING_URL         = SECURITY_PATH+"/sign-in";
	public static final String USERNAME_PARAMETER           = "username";
	public static final String USERPWD_PARAMETER            = "password";
	public static final String DEFAULT_SUCCESS_URL          = "/";

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
				"/assets/**",
				"/*.css",
				"/*.js",
				"/*.map",
				"/*.ico",
				"/websocket",
				"/websocket/**",
				"/anon-apis",
				"/anon-apis/**",
//				"/*.js"
				"/h2-console",
				"/h2-console/**",
//				"/*.map",
				"/favicon.ico"
		);
	}

	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.formLogin()
				.loginPage(LOGIN_PAGE)                       //로그인 페이지
				.loginProcessingUrl(LOGIN_PROCESSING_URL)     //login-processing-url  로그인 페이지 form action에 입력할 주소 지정
				.usernameParameter(USERNAME_PARAMETER)
				.passwordParameter(USERPWD_PARAMETER)
				.defaultSuccessUrl(DEFAULT_SUCCESS_URL)       //성공시 이동될 페이지
//				.failureHandler(authenticationFailureHandler())
//				.successHandler(new AuthenticationSuccessHandler())
				.permitAll()
				.and()
				.logout()
					.logoutUrl(LOGOUT_URL)
					.invalidateHttpSession(true)
					.logoutSuccessUrl(DEFAULT_SUCCESS_URL)
				.and()
				.addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, tokenService, userRepository,objectMapper))
				.addFilterAfter(new JwtTokenVerifier(jwtConfig, tokenService), JwtUsernameAndPasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers("/", "index","/favicon.ico", "*.css","/css/*", "/js/*").permitAll()
				.antMatchers("/apis","/apis/**").hasAnyRole(UserRole.USER.name(), UserRole.ADMIN.name())
				.antMatchers("/h2-console","/h2-console/**").hasAnyRole(UserRole.ADMIN.name())
				.antMatchers("/swagger", "/swagger/**").hasRole(UserRole.ADMIN.name())
				.antMatchers("/docs", "/docs/**").hasRole(UserRole.ADMIN.name())
//				.antMatchers("/", "/w","/apis/*").permitAll()
				.anyRequest()
				.authenticated();


	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}

	//	@Bean
//	public AuthenticationSuccessHandler authenticationSuccessHandler() {
//		return new AuthenticationSuccessHandler();
//	}
//
//	@Bean
//	public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
//		return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
//	}
//
//	@Bean
//	public AccessDeniedHandler accessDeniedHandler() {
//		return new AccessDeniedHandler();
//	}

}
