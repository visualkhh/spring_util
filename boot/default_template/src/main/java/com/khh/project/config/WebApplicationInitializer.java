package com.khh.project.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Slf4j
@Component
public class WebApplicationInitializer implements org.springframework.web.WebApplicationInitializer {


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        log.debug("##### WebApplicationInitializer onStartup #####"+servletContext.toString());
//        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        ctx.register(WebMvcConfigurerAdapter.class);
//        ctx.setServletContext(servletContext);
//        Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
//        dynamic.addMapping("/");
//        dynamic.setLoadOnStartup(1);

    }
}
