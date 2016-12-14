package com.khh.project.config.web.security.oauth;

import com.khh.project.config.web.security.AuthenticationProvider;
import com.khh.project.config.web.security.AuthenticationSuccessHandler;
import com.khh.project.config.web.security.LogoutSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.vote.ScopeVoter;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Configuration
@Slf4j
@Order(-3)
public class ResourceServerConfigurerAdapter extends org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter{
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		super.configure(resources);
	}


	public static final String ROOT_PATH                	= "/";
	public static final String SECURITY_PATH            	= "/security";
	public static final String ANON_PATH                	= "/anon";
	public static final String AUTH_PATH                	= "/auth";

	public static final String LOGIN_PAGE               	= SECURITY_PATH+"/login";
	public static final String LOGIN_PROCESSING_URL     	= SECURITY_PATH+"/sign_in";
	public static final String FAILURE_URL              	= LOGIN_PAGE;
	public static final String USERNAME_PARAMETER       	= "username";
	public static final String PASSWORD_PARAMETER       	= "password";
	public static final String DEFAULT_SUCCESS_URL      	= ROOT_PATH;
	public static final String LOGOUT_SUCCESS_URL       	= ROOT_PATH;
	public static final String SESSION_EXPIRED_URL      	= LOGIN_PAGE+"?expred";
	public static final String SESSION_INVALIDSESSION_URL	= LOGIN_PAGE+"?invalid";
	public static final String LOGOUT_URL               	= SECURITY_PATH+"/sign_out";
	public static final String REMEMBER_ME_KEY          	= "REMEBMER_ME_KEY";
	public static final String REMEMBER_ME_COOKE_NAME   	= "REMEMBER_ME_COOKE";

	@Autowired
	AuthenticationProvider authenticationProvider;
	@Autowired
	AuthenticationSuccessHandler authenticationSuccessHandler;
	@Autowired
	LogoutSuccessHandler logoutSuccessHandler;
	@Autowired
	RememberMeServices tokenBasedRememberMeServices;


	private TokenExtractor tokenExtractor = new BearerTokenExtractor();


	@Override
	public void configure(HttpSecurity http) throws Exception {
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
                .successHandler(authenticationSuccessHandler)
                .permitAll()
                .and()
            .rememberMe()
                .key(REMEMBER_ME_KEY)
                .rememberMeServices(tokenBasedRememberMeServices)
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
            .authenticationProvider(authenticationProvider)	//configure(AuthenticationManagerBuilder auth) 오버라이딩해서 추가할수도있다.
            .csrf().disable();
	}

//	@Override
//	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//		resources.resourceId("visualkhh");
//	}
}
