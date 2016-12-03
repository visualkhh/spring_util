package com.khh.project.config.web;

import com.khh.project.config.web.security.*;
import com.khh.project.config.web.security.user.LoginUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
public class WebSecurityConfigurerAdapter extends org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter {

    @Value("${spring.h2.console.enabled}")
    boolean h2ConsoleEnabled;

    public static final String ROOT_PATH                = "/";
    public static final String SECURITY_PATH            = "/security";
    public static final String ANON_PATH                = "/anon";
    public static final String AUTH_PATH                = "/auth";

    public static final String LOGIN_PAGE               = SECURITY_PATH+"/login";
    public static final String LOGIN_PROCESSING_URL     = SECURITY_PATH+"/sign_in";
    public static final String FAILURE_URL              = SECURITY_PATH+"/fail";
    public static final String USERNAME_PARAMETER       = "username";
    public static final String PASSWORD_PARAMETER       = "password";
    public static final String DEFAULT_SUCCESS_URL      = ROOT_PATH;
    public static final String LOGOUT_SUCCESS_URL       = ROOT_PATH;
    public static final String LOGOUT_URL               = SECURITY_PATH+"/sign_out";
    public static final String REMEMBER_ME_KEY          = "REMEBMER_ME_KEY";

    public static final String REMEMBER_ME_COOKE_NAME   = "REMEMBER_ME_COOKE";



    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private LoginUserDetailsService userDetailsService;
	@Autowired
	AuthenticationManager authenticationManager;
    @Autowired
	private DataSource dataSource;


    @Override
    public void configure(WebSecurity web) throws Exception {
        if(h2ConsoleEnabled) {
            web.ignoring().antMatchers("/h2-console/**");
        }
		web.ignoring().antMatchers("/resource/**","/static/**","/img/**","/image/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.debug("-----security HttpSecurity-----"+http);
        http
            .anonymous()
                .and()
			.addFilterBefore(filterSecurityInterceptor(), UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
                //.antMatchers("/", ANON_PATH +"/**", SECURITY_PATH+"/**").permitAll()
                .antMatchers("/", ANON_PATH +"/**").permitAll()
                .antMatchers(AUTH_PATH +"/**").hasRole("AUTH")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/board/**").hasRole("USER")
//                .antMatchers("/board/**").hasAnyAuthority()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage(LOGIN_PAGE)                       //로그인 페이지
                .loginProcessingUrl(LOGIN_PROCESSING_URL)     //login-processing-url  로그인 페이지 form action에 입력할 주소 지정
                .failureUrl(FAILURE_URL)                     //실패시 이동될 페이지
                .usernameParameter(USERNAME_PARAMETER)
                .passwordParameter(PASSWORD_PARAMETER)
                .defaultSuccessUrl(DEFAULT_SUCCESS_URL)       //성공시 이동될 페이지
                .failureHandler(new AuthenticationFailureHandler())
                .successHandler(new AuthenticationSuccessHandler())
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
//                .logoutSuccessHandler(new LogoutSuccessHandler())
                .logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_URL))
                .permitAll()
                .and()
            .authenticationProvider(authenticationProvider)
            .csrf();




/*
        httpSecurity
//                .csrf().disable()
                .anonymous()
                .and()
                .authorizeRequests()
                .antMatchers("/index", "/user/**", "/space", "/space/**", "/feed/**", "/qa", "/qa/**", "/qa/save", "/wiki/**", "/common/**").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .and()
                .formLogin()
                .LOGIN_PAGE("/LOGIN_PAGE").USERNAME_PARAMETER("id").PASSWORD_PARAMETER("pwd")
                .LOGIN_PROCESSING_URL("/user/login/authenticate").permitAll()
//                .successHandler(loginSuccessHandler())
//                .failureHandler(new AuthenticationFailureHandler())
                .FAILURE_URL("/login?error")
                .and()
//                .rememberMe().key(REMEMBER_ME_KEY).rememberMeServices(tokenBasedRememberMeServices())
//                .and()
                .logout().deleteCookies("remember-me").LOGOUT_URL("/logoutUser").LOGOUT_SUCCESS_URL("/")
                .and()
                .exceptionHandling().accessDeniedPage("/access?error");
*/
        //.logout().logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))

    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        log.debug(auth.toString());
////        auth.authenticationProvider(daoAuthenticationProvider());
//    }


//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsServiceImpl);
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
//        return daoAuthenticationProvider;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
    @Bean
    public RememberMeServices tokenBasedRememberMeServices() {
        TokenBasedRememberMeServices tokenBasedRememberMeServices = new TokenBasedRememberMeServices(REMEMBER_ME_KEY, userDetailsService);
        tokenBasedRememberMeServices.setAlwaysRemember(true);
        tokenBasedRememberMeServices.setTokenValiditySeconds(60 * 60 * 24 * 31);
        tokenBasedRememberMeServices.setCookieName(REMEMBER_ME_COOKE_NAME);
        return tokenBasedRememberMeServices;
    }


    ////////http://aoruqjfu.fun25.co.kr/index.php/post/657
	@Bean
	public FilterSecurityInterceptor filterSecurityInterceptor() {
		FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
		filterSecurityInterceptor.setAuthenticationManager(authenticationManager);
		filterSecurityInterceptor.setSecurityMetadataSource(filterInvocationSecurityMetadataSource());
		filterSecurityInterceptor.setAccessDecisionManager(affirmativeBased());
		return filterSecurityInterceptor;
	}

	@Bean
	public AffirmativeBased affirmativeBased() {
		List<AccessDecisionVoter<? extends Object>> accessDecisionVoters = new ArrayList<>();
		accessDecisionVoters.add(roleVoter());
		AffirmativeBased affirmativeBased = new AffirmativeBased(accessDecisionVoters);
		return affirmativeBased;
	}

	@Bean
	public RoleHierarchyVoter roleVoter() {
		RoleHierarchyVoter roleHierarchyVoter = new RoleHierarchyVoter(roleHierarchy());
		roleHierarchyVoter.setRolePrefix("ROLE_");
		return roleHierarchyVoter;
	}

	//RoleHierarchy 설정
	@Bean
	public RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
		return roleHierarchy;
	}

	//시큐리트쪽 부분에서 사용자가 화면 페이지 호출하면 매번 호출되는 클래스 중요함
	@Bean
	public FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource(){
		return new FilterInvocationSecurityMetadataSource();
	}

//    @Bean
//    public AuthenticationSuccessHandler loginSuccessHandler() {
//        log.debug("#### login Success handler #####");
//        return new AuthenticationSuccessHandler();
//    }
//

	//REMEMBER ME를 위한.
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
//        tokenRepositoryImpl.setDataSource(dataSource);
//        return tokenRepositoryImpl;
//    }

}
