package com.ceragem.iot.cms.config;

import com.ceragem.iot.cms.config.security.WebSecurityConfigurerAdapter;
import com.ceragem.iot.cms.controller.AnonsController;
import com.ceragem.iot.core.config.properties.ProjectProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.util.Locale;

@Configuration
@EnableConfigurationProperties(ProjectProperties.class)
public class CmsWebMvcConfigurerAdapter implements WebMvcConfigurer {

        @Value("${project.properties.angular-path}")
        private String angularPath;

        @Bean
        public LocaleResolver localeResolver() {
            SessionLocaleResolver slr = new SessionLocaleResolver();
            slr.setDefaultLocale(Locale.KOREAN);
            return slr;
        }

        public LocaleChangeInterceptor localeChangeInterceptor() {
            LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
            lci.setParamName(AnonsController.LANG_CHANGE_PARAM_NAME);
            return lci;
        }
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(localeChangeInterceptor()).addPathPatterns(AnonsController.URI_PREFIX + AnonsController.LANG_CHANGE_URI);
        }


        //로그인페이지.
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController(WebSecurityConfigurerAdapter.LOGIN_PAGE).setViewName(WebSecurityConfigurerAdapter.LOGIN_PAGE);
        }

        //리소스 패스 설정
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/resources/**")            .addResourceLocations("/WEB-INF/resources/");
            registry.addResourceHandler("/webjars/**")              .addResourceLocations("classpath:/META-INF/resources/webjars/");

            registry.addResourceHandler("/assets/**")	            .addResourceLocations(angularPath+"/assets/");
            registry.addResourceHandler("/favicon.ico")	            .addResourceLocations(angularPath+"/favicon.ico");
            registry.addResourceHandler("/*.html")	                .addResourceLocations(angularPath+"/");
            registry.addResourceHandler("/*.map")	                .addResourceLocations(angularPath+"/");
            registry.addResourceHandler("/*.js")	                .addResourceLocations(angularPath+"/");
            registry.addResourceHandler("/*.css")	                .addResourceLocations(angularPath+"/");
        }



}
