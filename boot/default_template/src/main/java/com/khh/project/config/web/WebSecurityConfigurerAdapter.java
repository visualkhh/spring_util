package com.khh.project.config.web;

import com.khh.project.config.web.security.*;
import com.khh.project.config.web.security.user.LoginUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


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

    public static final String REMEMBER_ME_COOKE_NAME  = "REMEMBER_ME_COOKE";



    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private LoginUserDetailsService userDetailsService;

//    private final static String REMEMBER_ME_KEY = "LIBQA_REMEBMER_ME_KEY";
//
//    @Autowired
//    private CustomAuthenticationProvider customAuthenticationProvider;
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsServiceImpl;

//    @Override
//    public void configure(WebSecurity webSecurity) throws Exception {
//        log.debug("-----security ignore-----");
//        webSecurity.ignoring().antMatchers("/", "/resource/**");
//    }

//    @Autowired
    //private LoginUserRepository loginUserRepository;


    @Override
    public void configure(WebSecurity web) throws Exception {
        if(h2ConsoleEnabled) {
            web.ignoring().antMatchers("/h2-console/**");
        }
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.debug("-----security HttpSecurity-----"+http);
        http
            .anonymous()
                .and()
            .authorizeRequests()
                //.antMatchers("/", ANON_PATH +"/**", SECURITY_PATH+"/**").permitAll()
                .antMatchers("/", ANON_PATH +"/**").permitAll()
                .antMatchers(AUTH_PATH +"/**").hasRole("AUTH")
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/board/**").hasRole("USER")
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
//
//    @Bean
//    public AuthenticationSuccessHandler loginSuccessHandler() {
//        log.debug("#### login Success handler #####");
//        return new AuthenticationSuccessHandler();
//    }
//
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
//        tokenRepositoryImpl.setDataSource(dataSource);
//        return tokenRepositoryImpl;
//    }

}
