package com.khh.project.config.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@Slf4j
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
public class WebSecurityConfigurerAdapter extends org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter {

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
    protected void configure(HttpSecurity http) throws Exception {
        log.debug("-----security HttpSecurity-----"+http);
        http
            .authorizeRequests()
            .antMatchers("/", "/home").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginProcessingUrl("/security/sign_in")   //login-processing-url  로그인 페이지 form action에 입력할 주소 지정
            .loginPage("/security/login").usernameParameter("id").passwordParameter("pwd").defaultSuccessUrl("/")
            .permitAll()
            .and()
            .logout().deleteCookies("remember-me").logoutUrl("/security/sign_out").logoutSuccessUrl("/")
            .permitAll();

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
                .loginPage("/loginPage").usernameParameter("id").passwordParameter("pwd")
                .loginProcessingUrl("/user/login/authenticate").permitAll()
//                .successHandler(loginSuccessHandler())
//                .failureHandler(new CustomLoginFailureHandler())
                .failureUrl("/login?error")
                .and()
//                .rememberMe().key(REMEMBER_ME_KEY).rememberMeServices(tokenBasedRememberMeServices())
//                .and()
                .logout().deleteCookies("remember-me").logoutUrl("/logoutUser").logoutSuccessUrl("/")
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
//    @Bean
//    public RememberMeServices tokenBasedRememberMeServices() {
//        TokenBasedRememberMeServices tokenBasedRememberMeServices = new TokenBasedRememberMeServices(REMEMBER_ME_KEY, userDetailsServiceImpl);
//        tokenBasedRememberMeServices.setAlwaysRemember(true);
//        tokenBasedRememberMeServices.setTokenValiditySeconds(60 * 60 * 24 * 31);
//        tokenBasedRememberMeServices.setCookieName("libQaCookie");
//        return tokenBasedRememberMeServices;
//    }
//
//    @Bean
//    public AuthenticationSuccessHandler loginSuccessHandler() {
//        log.debug("#### login Success handler #####");
//        return new CustomLoginSuccessHandler();
//    }
//
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
//        tokenRepositoryImpl.setDataSource(dataSource);
//        return tokenRepositoryImpl;
//    }

}
