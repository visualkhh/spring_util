package com.khh.project.config.web;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Slf4j
public class WebApplicationInitializer implements org.springframework.web.WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        log.debug("##### WebApplicationInitializer onStartup #####");
//        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        ctx.register(WebMvcConfigurerAdapter.class);
//        ctx.setServletContext(servletContext);
//        Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
//        dynamic.addMapping("/");
//        dynamic.setLoadOnStartup(1);

    }
}
