package com.ceragem.iot.api.config.security;


import com.ceragem.iot.api.config.security.auth.UserDetailsService;
import com.ceragem.iot.api.config.security.jwt.JwtConfig;
import com.ceragem.iot.api.config.security.jwt.JwtTokenVerifier;
import com.ceragem.iot.api.config.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.ceragem.iot.api.code.UserRole;
import com.ceragem.iot.api.controller.securitys.SecuritysController;
import com.ceragem.iot.api.repository.UserRepository;
import com.ceragem.iot.api.service.TokenService;
import com.ceragem.iot.core.repository.CoreUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfigurerAdapter extends org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter {


	@Value("${project.properties.username}")
	String username;

	@Value("${project.properties.password}")
	String password;

	public static final String SECURITY_PATH                = "/securitys";
	public static final String LOGOUT_URL                   = SECURITY_PATH+"/sign-out";
	public static final String LOGIN_PAGE                   = "/#/login";
	public static final String LOGIN_PROCESSING_URL         = SECURITY_PATH+"/sign-in";
	public static final String USERNAME_PARAMETER           = "username";
	public static final String USERPWD_PARAMETER            = "password";
	public static final String DEFAULT_SUCCESS_URL          = "/";
	//	@Autowired
//	PasswordEncoder passwordEncoder;

	private final PasswordEncoder passwordEncoder;
	private final UserDetailsService applicationUserService;
	private final UserRepository userRepository;
	private final JwtConfig jwtConfig;
	private final CacheManager cacheManager;
	private final TokenService tokenService;

	@Autowired
	public WebSecurityConfigurerAdapter(PasswordEncoder passwordEncoder,
										UserDetailsService applicationUserService,
										JwtConfig jwtConfig,
										CacheManager cacheManager,
										TokenService tokenService,
										UserRepository userRepository
	) {
		this.userRepository = userRepository;
		this.cacheManager = cacheManager;
		this.tokenService = tokenService;
		this.passwordEncoder = passwordEncoder;
		this.applicationUserService = applicationUserService;
		this.jwtConfig = jwtConfig;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
				"/assets/**",
				"/*.css",
				"/*.js",
				"/*.map",
				"/*.ico",
				SecuritysController.URI_PREFIX+"/refresh"
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
				.addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, cacheManager, tokenService, userRepository))
				.addFilterAfter(new JwtTokenVerifier(jwtConfig, cacheManager, tokenService),JwtUsernameAndPasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers("/", "index","/favicon.ico", "*.css","/css/*", "/js/*").permitAll()
				.antMatchers("/apis" ,"/apis/**").hasAnyRole(UserRole.USER.name(), UserRole.ADMIN.name())
				.antMatchers("/swagger", "/swagger/**").hasRole(UserRole.SWAGGER.name())
				.antMatchers("/docs", "/docs/**").hasRole(UserRole.DOCS.name())
				.anyRequest()
				.authenticated();
//				.and().httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(applicationUserService);
		return provider;
	}

//	@Bean
//	public AuthenticationFailureHandler authenticationFailureHandler() {
//		return new AuthenticationFailureHandler();
//	}
	//로그아웃 성공시 핸들링
//	@Bean
//	public LogoutSuccessHandler logoutSuccessHandler(){
//		return new LogoutSuccessHandler();
//	}

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
