package com.khh.project.config.web;

import com.khh.project.config.web.security.*;
import com.khh.project.config.web.security.user.LoginUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AnonymousConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.criterion.Restrictions.and;


@Slf4j
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
//@Order(Ordered.HIGHEST_PRECEDENCE)
//@Order(2)
public class WebSecurityConfigurerAdapter extends org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter {

    @Value("${spring.h2.console.enabled}")
    boolean h2ConsoleEnabled;
    @Value("${spring.h2.console.path}")
    String h2ConsolePath;

    public static final String ROOT_PATH                	= "/";
    public static final String SECURITY_PATH            	= "/security";
    public static final String ANON_PATH                	= "/anon";
    public static final String AUTH_PATH                	= "/auth";

    public static final String LOGIN_PAGE               	= SECURITY_PATH + "/login";
    public static final String LOGIN_PROCESSING_URL     	= SECURITY_PATH + "/sign_in";
    public static final String FAILURE_URL              	= LOGIN_PAGE;
    public static final String USERNAME_PARAMETER       	= "username";
    public static final String PASSWORD_PARAMETER       	= "password";
    public static final String DEFAULT_SUCCESS_URL      	= ROOT_PATH;
    public static final String LOGOUT_SUCCESS_URL       	= ROOT_PATH;
    public static final String SESSION_EXPIRED_URL      	= LOGIN_PAGE + "?expred";
    public static final String SESSION_INVALIDSESSION_URL	= LOGIN_PAGE + "?invalid";
    public static final String LOGOUT_URL               	= SECURITY_PATH + "/sign_out";
    public static final String REMEMBER_ME_KEY          	= "REMEBMER_ME_KEY";
    public static final String REMEMBER_ME_COOKE_NAME   	= "REMEMBER_ME_COOKE";



//    @Autowired
//    private LoginUserDetailsService userDetailsService;
	@Autowired
	AuthenticationManager authenticationManager;
//    @Autowired
//	private DataSource dataSource;


    @Override
    public void configure(WebSecurity web) throws Exception {
        if(h2ConsoleEnabled) {
            web.ignoring().antMatchers(h2ConsolePath+"/**");
        }
		web.ignoring().antMatchers(
				"/resource/**"              ,
				"/static/**"                ,
				"/img/**"                   ,
				"/image/**"                 ,
				"/assets/**"                ,
				"/*.js"                     ,
				"/*.map"
//				"/0.chunk.js"               ,
//				"/index.html"               ,
//				"/inline.bundle.js"         ,
//				"/main.bundle.js"           ,
//				"/polyfills.bundle.js"      ,
//				"/styles.bundle.js"         ,
//				"/vendor.bundle.js"         ,
//				"/"
		);
//		web.ignoring().antMatchers("/resource/**", "/static/**", "/img/**", "/image/**", "/oauth/**");
	}


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.debug("-----security HttpSecurity-----"+http);
        http
            .anonymous()
				.and()
			//커스텀 시큐리티 인텁셉터 하려면 아래를 사용하면된다
//			.addFilterBefore(filterSecurityInterceptor(), UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
                .antMatchers("/", ANON_PATH +"/**")		.permitAll()
                .antMatchers("/admin/**")				.hasRole("ADMIN")
                .antMatchers("/board/**")				.hasRole("USER")
//                .antMatchers(AUTH_PATH +"/**")			.authenticated()
                .anyRequest().authenticated()
                .and()
			.sessionManagement()							//http://niees.tistory.com/17
				.maximumSessions(1)
				.expiredUrl(SESSION_EXPIRED_URL)			//중복 로그인이 일어났을 경우 이동 할 주소​
				.maxSessionsPreventsLogin(false)			//만약 두번째 인증을 거부하게 하고 싶은 경우concurrency-control에​ error-if-maximum-exceeded="true"속성을 지정하면 된다.​
				.and()
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				.invalidSessionUrl(SESSION_INVALIDSESSION_URL)
				.and()
            .formLogin()
                .loginPage(LOGIN_PAGE)                       //로그인 페이지
                .loginProcessingUrl(LOGIN_PROCESSING_URL)     //login-processing-url  로그인 페이지 form action에 입력할 주소 지정
                .failureUrl(FAILURE_URL)                     //실패시 이동될 페이지
                .usernameParameter(USERNAME_PARAMETER)
                .passwordParameter(PASSWORD_PARAMETER)
                .defaultSuccessUrl(DEFAULT_SUCCESS_URL)       //성공시 이동될 페이지
                //.failureHandler(authenticationFailureHandler())
                .successHandler(authenticationSuccessHandler())
                .permitAll()
                .and()
            .rememberMe()
                .key(REMEMBER_ME_KEY)
                .rememberMeServices(tokenBasedRememberMeServices())
                .and()
            .logout()
                .deleteCookies(REMEMBER_ME_COOKE_NAME)
                .deleteCookies("JSESSIONID")
                .logoutUrl(LOGOUT_URL)
                .invalidateHttpSession(true)
                .logoutSuccessUrl(LOGOUT_SUCCESS_URL)
//                .logoutSuccessHandler(logoutSuccessHandler())      //커스텀으로 로그아웃된거에 대한 처리를 해주면 로그아웃성공URL로 가지 않으니 커스텀할떄 사용해여라
                .logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_URL))
                .permitAll()
                .and()
            .authenticationProvider(authenticationProvider())	//configure(AuthenticationManagerBuilder auth) 오버라이딩해서 추가할수도있다.
            .csrf();
    }


///////////////////////////AuthenticationProvider/////////////////////////////////////////////////
/**/     //AuthenticationProvider Interface는 자동으로 클래스패스에 있는거 찾아서 쓴다
/**/ //
/**/ //	//DaoAuthenticationProvider daoAuthenticationProvider()  대신  이거 아래 처럼 등록을해도된다
/**/ //@Override
/**/ //protected void configure(AuthenticationManagerBuilder auth) throws Exception {
/**/ //	auth.authenticationProvider(authenticationProvider());
/**/ //}
/**/ //	//메모리로 사용하고싶을때
/**/ //	@Override
/**/ //	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
/**/ //		auth.inMemoryAuthentication().withUser("min").password("min").roles("USER");
/**/ //	}
/**/ //    @Bean //스프링에서 제공하는 기본적인 아이디 idpassword관련 처리
/**/ //    public DaoAuthenticationProvider daoAuthenticationProvider() {
/**/ //        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
/**/ //        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
/**/ //        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
/**/ //        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
/**/ //        return daoAuthenticationProvider;
/**/ //    }
/**/	@Bean
/**/	public AuthenticationProvider authenticationProvider(){
/**/		return new AuthenticationProvider();
/**/	}
/////////////////////////////////////////////////////////////////////////////////////////////////




///////////////////////////////////////////////////////////////////////////////////////////////////////
/**/	//커스텀 시큐리티 인텁셉터 하려면 아래를 사용하면된다
/**/	//사용전에  httpSecurity쪽에 추가해줘야된다  .addFilterBefore(filterSecurityInterceptor(), UsernamePasswordAuthenticationFilter.class)
/**/    ////////http://aoruqjfu.fun25.co.kr/index.php/post/657
/**/	@Bean
/**/	public FilterSecurityInterceptor filterSecurityInterceptor() {
/**/		FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
/**/		filterSecurityInterceptor.setAuthenticationManager(authenticationManager);
/**/		filterSecurityInterceptor.setSecurityMetadataSource(filterInvocationSecurityMetadataSource());
/**/		filterSecurityInterceptor.setAccessDecisionManager(affirmativeBased());
/**/		return filterSecurityInterceptor;
/**/	}
/**/	@Bean
/**/	public AffirmativeBased affirmativeBased() {
/**/		List<AccessDecisionVoter<? extends Object>> accessDecisionVoters = new ArrayList<>();
/**/		accessDecisionVoters.add(roleVoter());
/**/		AffirmativeBased affirmativeBased = new AffirmativeBased(accessDecisionVoters);
/**/		return affirmativeBased;
/**/	}
/**/	@Bean
/**/	public RoleHierarchyVoter roleVoter() {
/**/		RoleHierarchyVoter roleHierarchyVoter = new RoleHierarchyVoter(roleHierarchy());
/**/		roleHierarchyVoter.setRolePrefix("ROLE_");
/**/		return roleHierarchyVoter;
/**/	}
/**/	//RoleHierarchy 설정
/**/	@Bean
/**/	public RoleHierarchy roleHierarchy() {
/**/		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
/**/		roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
/**/		return roleHierarchy;
/**/	}
/**/	//시큐리트쪽 부분에서 사용자가 화면 페이지 호출하면 매번 호출되는 클래스 중요함
/**/	@Bean
/**/	public FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource(){
/**/		return new FilterInvocationSecurityMetadataSource();
/**/	}
///////////////////////////////////////////////////////////////////////////////////////////////////




	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new PasswordEncoder();
	}
	@Bean
	public LoginUserDetailsService userDetailsService(){
		return new LoginUserDetailsService();
	}
	@Bean
	public RememberMeServices tokenBasedRememberMeServices() {
		TokenBasedRememberMeServices tokenBasedRememberMeServices = new TokenBasedRememberMeServices(REMEMBER_ME_KEY, userDetailsService());
		tokenBasedRememberMeServices.setAlwaysRemember(true);
		tokenBasedRememberMeServices.setTokenValiditySeconds(60 * 60 * 24 * 31);
		tokenBasedRememberMeServices.setCookieName(REMEMBER_ME_COOKE_NAME);
		return tokenBasedRememberMeServices;
	}


	//login,out 정상처리 및 실패에 대한 Bean
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        log.debug("#### login Success handler #####");
        return new AuthenticationSuccessHandler();
    }
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        log.debug("#### login Failurer handler #####");
        return new AuthenticationFailureHandler();
    }
	//로그아웃 성공시 핸들링
    @Bean
	public LogoutSuccessHandler logoutSuccessHandler(){
		return new LogoutSuccessHandler();
	}


   // https://github.com/sbcoba/spring-boot-oauth2-sample/blob/master/src/main/java/com/example/DemoApplication.java
//	@Bean
//	public TokenStore JdbcTokenStore(DataSource dataSource) {
//		return new JdbcTokenStore(dataSource);
//	}
//   @Override
//   protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
//	   authManagerBuilder
//			   .inMemoryAuthentication()
//			   .withUser("user1").password("password1").roles("USER").and()
//			   .withUser("admin1").password("password1").roles("ADMIN");
//   }
//
//	@Bean
//	@Override
//	public AuthenticationManager authenticationManager() throws Exception {
//		return super.authenticationManager();
//	}


	//REMEMBER ME를 위한.
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
//        tokenRepositoryImpl.setDataSource(dataSource);
//        return tokenRepositoryImpl;
//    }

}
