package com.today.house.config.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@Order(SecurityProperties.BASIC_AUTH_ORDER)
//@Order(Integer.MAX_VALUE)
@EnableWebSecurity
public class WebSecurityConfigurerAdapter extends org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter {


	public static final String ROOT_PATH                    = "/";
	public static final String SECURITY_PATH                = "/securitys";
	public static final String ANON_PATH                    = "/anons";
	public static final String API_PATH                    = "/apis";
	public static final String AUTH_PATH                    = "/auths";

	public static final String LOGIN_PAGE                   = "/#/login";
	public static final String LOGIN_PROCESSING_URL         = SECURITY_PATH+"/sign-in";
	public static final String USERNAME_PARAMETER           = "username";
	public static final String USERPWD_PARAMETER            = "password";
	public static final String DEFAULT_SUCCESS_URL          = "/";
	public static final String SESSION_EXPIRED_URL          = LOGIN_PAGE+"?expred";
	public static final String SESSION_INVALIDSESSION_URL   = LOGIN_PAGE+"?invalid";
	public static final String LOGOUT_URL                   = SECURITY_PATH+"/sign-out";
	public static final String REMEMBER_ME_KEY              = "REMEBMER_ME_KEY";
	public static final String REMEMBER_ME_COOKE_NAME       = "REMEMBER_ME_COOKE";


//	@Value("${project.properties.resource.image-resource-path}")
//	private String imageResourcePath = null;
//	@Value("${project.properties.resource.music-resource-path}")
//	private String musicResourcePath = null;
//	@Value("${project.properties.resource.video-resource-path}")
//	private String videoResourcePath = null;
//	@Value("${project.properties.resource.game-resource-path}")
//	private String gameResourcePath = null;
//	@Value("${project.properties.resource.zip-resource-path}")
//	private String zipResourcePath = null;

//	@Autowired
//	AuthenticationProvider authenticationProvider;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
				"/resources/**",
				"/webjars/**",
//				"/swagger-ui.html",

				"/assets/**",
				"/web-core-assets/**",
				"/*.js",
				"/*.map",
				"/favicon.ico",
				API_PATH+"/**"
//				ANON_PATH+"/**",
//				imageResourcePath+"/**",
//				musicResourcePath+"/**",
//				videoResourcePath+"/**",
//				gameResourcePath+"/**",
//				zipResourcePath+"/**"
		);
	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.debug("-----security HttpSecurity-----"+http);

		//setting arg/////////////////////
		List<AccessDecisionVoter<? extends Object>> accessDecisionVoters = new ArrayList<>();
		RoleVoter roleVoter = new RoleVoter();
//		roleVoter.setRolePrefix("");
		accessDecisionVoters.add(roleVoter);
		AffirmativeBased affirmativeBased = new AffirmativeBased(accessDecisionVoters);
		affirmativeBased.setAllowIfAllAbstainDecisions(false);
		FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
		filterSecurityInterceptor.setAuthenticationManager(authenticationManagerBean());
		filterSecurityInterceptor.setAccessDecisionManager(affirmativeBased);
		filterSecurityInterceptor.setSecurityMetadataSource(new FilterInvocationSecurityMetadataSource());
		/////////////////////////////////////////////



		http
				.anonymous()
				.and()
			    .csrf()
//				.ignoringAntMatchers(ANON_PATH+"/**")
				.ignoringAntMatchers(LOGOUT_URL)
//				.requireCsrfProtectionMatcher(requestMatcher())
				.csrfTokenRepository(csrfTokenRepository())
				.and()
				.authorizeRequests()
//				.antMatchers("/swagger-ui.html").hasRole("AUTH")//authenticated()//
				//				.antMatchers("/", ANON_PATH +"/**") .permitAll()
//				.antMatchers(AUTH_PATH +"/**")      .hasRole("AUTH")
//				.antMatchers("/super/**")           .hasRole("SUPER")
//				.antMatchers("/**").hasAnyAuthority()
				.anyRequest().hasAnyAuthority()
//					.anyRequest().authenticated()
				.and()
				.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
//				.sessionManagement()                     //http://niees.tistory.com/17
////				.sessionAuthenticationStrategy(sessionAuthenticationStrategy())
//				.maximumSessions(1)
//				.sessionRegistry(sessionRegistry())
//				.expiredUrl(SESSION_EXPIRED_URL)         //중복 로그인이 일어났을 경우 이동 할 주소​
//				.maxSessionsPreventsLogin(false)         //만약 두번째 인증을 거부하게 하고 싶은 경우concurrency-control에​ error-if-maximum-exceeded="true"속성을 지정하면 된다.​
//				.and()
//				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//				.invalidSessionUrl(SESSION_INVALIDSESSION_URL)
//				.and()
				.formLogin()
				.loginPage(LOGIN_PAGE)                       //로그인 페이지
				.loginProcessingUrl(LOGIN_PROCESSING_URL)     //login-processing-url  로그인 페이지 form action에 입력할 주소 지정
//				.failureUrl(AuthenticationFailureHandler.FAILURE_URL)                     //실패시 이동될 페이지
				.usernameParameter(USERNAME_PARAMETER)
				.passwordParameter(USERPWD_PARAMETER)
				.defaultSuccessUrl(DEFAULT_SUCCESS_URL)       //성공시 이동될 페이지
				.failureHandler(authenticationFailureHandler())
				.successHandler(this.authenticationSuccessHandler())
				.permitAll()
				.and()
//				.rememberMe()
//					.key(REMEMBER_ME_KEY)
//					.rememberMeServices(tokenBasedRememberMeServices())
//				.and()
				.logout()
					.deleteCookies(REMEMBER_ME_COOKE_NAME)
					.deleteCookies("JSESSIONID")
					.deleteCookies(CsrfHeaderFilter.CSRF_TOKEN_COOKE_NAME)
					.logoutUrl(LOGOUT_URL)
					.invalidateHttpSession(true)
//					.logoutSuccessUrl(LOGOUT_SUCCESS_URL)
                    .logoutSuccessHandler(logoutSuccessHandler())      //커스텀으로 로그아웃된거에 대한 처리를 해주면 로그아웃성공URL로 가지 않으니 커스텀할떄 사용해여라
//				.logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_URL))
				.permitAll()
				.and()
//				.authenticationProvider(authenticationProvider()) //configure(AuthenticationManagerBuilder auth) 오버라이딩해서 추가할수도있다.
//				.addFilterBefore(filterSecurityInterceptor(), FilterSecurityInterceptor.class)
				.addFilter(filterSecurityInterceptor)
//				.addFilter(concurrentSessionFilter());
//				.addFilter(filterSecurityInterceptor());
//				.addFilterBefore(filterSecurityInterceptor, BasicAuthenticationFilter.class)
//				.addFilterBefore(filterSecurityInterceptor(), BasicAuthenticationFilter.class)
//				.addFilterAfter(filterSecurityInterceptor(), UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
//				.exceptionHandling().accessDeniedPage("/accessDenied.jsp");
//				.exceptionHandling().accessDeniedHandler(accessDeniedHandler()).and()


	}

	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new AuthenticationSuccessHandler();
	}

	@Bean
	public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
		return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new AccessDeniedHandler();
	}
//	@Bean
//	public RequestMatcher requestMatcher() {
//		return new RequestMatcher() {
//			@Override
//			public boolean matches(HttpServletRequest request) {
//				if (request.getRequestURI().equals(LOGIN_PAGE)  || request.getRequestURI().indexOf(ANON_PATH) != -1) {
//					return false;
//				}
//				return true;
//			}
//		};
//	}


	///////////////////////////AuthenticationProvider/////////////////////////////////////////////////
/**/     //AuthenticationProvider Interface는 자동으로 클래스패스에 있는거 찾아서 쓴다
/**/ //
/**/ //    //DaoAuthenticationProvider daoAuthenticationProvider()  대신  이거 아래 처럼 등록을해도된다
/**/ //    @Override
/**/ //    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
/**/ //       auth.authenticationProvider(authenticationProvider());
/**/ //    }
/**/ //    @Bean //스프링에서 제공하는 기본적인 아이디 idpassword관련 처리
/**/ //    public DaoAuthenticationProvider daoAuthenticationProvider() {
/**/ //        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
/**/ //        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
/**/ //        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
/**/ //        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
/**/ //        return daoAuthenticationProvider;
/**/ //    }
///**/   @Bean
///**/   public AuthenticationProvider authenticationProvider(){
///**/      return new AuthenticationProvider();
///**/   }
/////////////////////////////////////////////////////////////////////////////////////////////////




	///////////////////////////////////////////////////////////////////////////////////////////////////////
/**/   //커스텀 시큐리티 인텁셉터 하려면 아래를 사용하면된다
/**/   //사용전에  httpSecurity쪽에 추가해줘야된다  .addFilterBefore(filterSecurityInterceptor(), UsernamePasswordAuthenticationFilter.class)
/**/    ////////http://aoruqjfu.fun25.co.kr/index.php/post/657
///**/    @Bean
///**/   public FilterSecurityInterceptor filterSecurityInterceptor() throws Exception {
///**/      FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
///**/      filterSecurityInterceptor.setAuthenticationManager(authenticationManager());
///**/      filterSecurityInterceptor.setAccessDecisionManager(affirmativeBased());
///**/      filterSecurityInterceptor.setSecurityMetadataSource(filterInvocationSecurityMetadataSource());
///**/      return filterSecurityInterceptor;
///**/   }
//	/**/@Bean
///**/   public AffirmativeBased affirmativeBased() {
///**/      List<AccessDecisionVoter<? extends Object>> accessDecisionVoters = new ArrayList<>();
/////**/      accessDecisionVoters.add(roleVoter());
///**/      accessDecisionVoters.add(new RoleVoter());
///**/      AffirmativeBased affirmativeBased = new AffirmativeBased(accessDecisionVoters);
///**/      return affirmativeBased;
///**/   }
//	/**/   @Bean
///**/   public RoleHierarchyVoter roleVoter() {
///**/      RoleHierarchyVoter roleHierarchyVoter = new RoleHierarchyVoter(roleHierarchy());
///**/      roleHierarchyVoter.setRolePrefix("ROLE_");
///**/      return roleHierarchyVoter;
///**/   }
//	/**/   //RoleHierarchy 설정
///**/   @Bean
///**/   public RoleHierarchy roleHierarchy() {
///**/      RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
///**/      roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
///**/      return roleHierarchy;
///**/   }
	/**/   //시큐리트쪽 부분에서 사용자가 화면 페이지 호출하면 매번 호출되는 클래스 중요함
///**/   @Bean
///**/   public FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource(){
///**/      return new FilterInvocationSecurityMetadataSource();
///**/   }
///////////////////////////////////////////////////////////////////////////////////////////////////


	@Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
//	@Bean
//	@Bean(name = "userDetailsService")
//	public UserDetailsService userDetailsService(){
//		return new UserDetailsService();
//	}

	//注册自定义的SessionRegistry
	@Bean
	public SessionRegistry sessionRegistry(){
		return new SessionRegistryImpl();
	}

	//注册自定义的sessionAuthenticationStrategy
	//ConcurrentSessionControlAuthenticationStrategy控制并发
	//SessionFixationProtectionStrategy可以防盗session
	//RegisterSessionAuthenticationStrategy触发了注册新sessin
//	@Bean
//	public CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy(){
//		ConcurrentSessionControlAuthenticationStrategy concurrentSessionControlAuthenticationStrategy=new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
//		concurrentSessionControlAuthenticationStrategy.setMaximumSessions(2);
//		concurrentSessionControlAuthenticationStrategy.setExceptionIfMaximumExceeded(true);
//		SessionFixationProtectionStrategy sessionFixationProtectionStrategy=new SessionFixationProtectionStrategy();
//		RegisterSessionAuthenticationStrategy registerSessionStrategy = new RegisterSessionAuthenticationStrategy(sessionRegistry());
//		CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy=new CompositeSessionAuthenticationStrategy(
//				Arrays.asList(concurrentSessionControlAuthenticationStrategy,sessionFixationProtectionStrategy,registerSessionStrategy));
//		return sessionAuthenticationStrategy;
//	}
//
//
//
//
//	//注册并发Session Filter
//	@Bean
//	public ConcurrentSessionFilter concurrentSessionFilter(){
//		SimpleRedirectSessionInformationExpiredStrategy s = new SimpleRedirectSessionInformationExpiredStrategy(SESSION_EXPIRED_URL);
//		return new ConcurrentSessionFilter(sessionRegistry(),s);
//	}

	//////csrf
	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-"+CsrfHeaderFilter.CSRF_TOKEN_COOKE_NAME);
		return repository;
	}


//	@Bean
//	public RememberMeServices tokenBasedRememberMeServices() {
//		TokenBasedRememberMeServices tokenBasedRememberMeServices = new TokenBasedRememberMeServices(REMEMBER_ME_KEY, userDetailsService());
//		tokenBasedRememberMeServices.setAlwaysRemember(true);
//		tokenBasedRememberMeServices.setTokenValiditySeconds(60 * 60 * 24 * 31);
//		tokenBasedRememberMeServices.setCookieName(REMEMBER_ME_COOKE_NAME);
//		return tokenBasedRememberMeServices;
//	}


	//login,out 정상처리 및 실패에 대한 Bean
//	@Bean
//	public AuthenticationSuccessHandler authenticationSuccessHandler() {
////		log.debug("#### login Success handler #####");
//		return new AuthenticationSuccessHandler();
//	}
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new AuthenticationFailureHandler();
	}
	//로그아웃 성공시 핸들링
	@Bean
	public LogoutSuccessHandler logoutSuccessHandler(){
		return new LogoutSuccessHandler();
	}
//	@Bean
//	public ActiveUserStore activeUserStore() {
//		return new ActiveUserStore();
//	}

//

	//REMEMBER ME를 위한.
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
//        tokenRepositoryImpl.setDataSource(dataSource);
//        return tokenRepositoryImpl;
//    }

}
