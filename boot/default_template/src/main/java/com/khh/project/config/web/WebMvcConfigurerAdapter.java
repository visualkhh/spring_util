package com.khh.project.config.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc
@Slf4j
public class WebMvcConfigurerAdapter extends org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter {
//    @Value("${libqa.viewResolver.cached}")
//    private String viewResolverCached;

    //로그인페이지.
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login").setViewName("login");
//    }


//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        super.addArgumentResolvers(argumentResolvers);
//    }

    //    @Override
//    public void addInterceptors(final InterceptorRegistry resistry) {
//        resistry.addInterceptor(new LoginUserHandlerInterceptor()).addPathPatterns("/", "/*", "/**", "/*/**");
//    }

//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }

//    @Bean
//    public ServletRegistrationBean h2servletRegistration() {
//        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
//        registration.addUrlMappings("/console/*");
//        return registration;
//    }

//    @Bean
//    public HandlebarsViewResolver viewResolver() throws Exception {
//        HandlebarsViewResolver viewResolver = new HandlebarsViewResolver();
//        viewResolver.setOrder(1);
//        viewResolver.setPrefix("/WEB-INF/views/");
//        viewResolver.setCache(BooleanUtils.toBoolean(viewResolverCached));
//        viewResolver.registerHelpers(new HandlebarsHelper());
//        return viewResolver;
//    }

//    @Bean
//    public EmbeddedServletContainerCustomizer containerCustomizer() {
//        return container -> {
//            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, ERROR_401);
//            ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, ERROR_403);
//            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, ERROR_404);
//            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_500);
//            container.addErrorPages(error401Page, error403Page, error404Page, error500Page);
//        };
//    }

//    @Bean
//    @ConfigurationProperties("spring.datasource")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resource/**").addResourceLocations("/resource/");
//    }


}
