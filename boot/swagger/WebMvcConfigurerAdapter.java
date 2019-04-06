package com.omnicns.omnifit2.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omnicns.omnifit2.api.model.OmnifitHeader;
import com.omnicns.omnifit2.api.service.DeviceService;
import com.omnicns.omnifit2.api.service.UserService;
import com.omnicns.omnifit2.common.code.MsgCode;
import com.omnicns.omnifit2.common.config.CommonWebMvcConfigurerAdapter;
import com.omnicns.omnifit2.common.config.properties.ProjectProperties;
import com.omnicns.omnifit2.common.exception.ErrorMsgException;
import com.omnicns.omnifit2.common.filter.RequestLoggingFilter;
import com.omnicns.omnifit2.common.model.error.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.validation.Valid;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Locale;

@Configuration
@Import({CommonWebMvcConfigurerAdapter.class})
@EnableWebMvc
@EnableConfigurationProperties(ProjectProperties.class)
@EnableScheduling
@Slf4j
public class WebMvcConfigurerAdapter extends org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter {

    @Value("${project.properties.header-name}")
    String headerName = null;
    @Autowired ProjectProperties projectProperties;
    @Autowired DeviceService deviceService;
    @Autowired UserService userService;


    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver slr = new AcceptHeaderLocaleResolver();
        slr.setDefaultLocale(Locale.KOREA);
//        List localeList = new ArrayList();
//        localeList.add(Locale.KOREA);
//        localeList.add(Locale.CHINA);
//        localeList.add(Locale.US);
//        slr.setSupportedLocales(localeList);
        return slr;
    }
    // preHandle  boolean    1. 클라이언트의 요청을 컨트롤러에 전달 하기 전에 호출
    //  false 인 경우 intercepter  또는 controller 를 실행 시키지 않고 요청 종료
    // postHandle     void   1. 컨트롤러 로직 실행 된 후 호출됨2. 컨트롤러 실행 도중 error 발생의 경우 postHandle() 는 실행  되지 않음
    //  request 로 넘어온 데이터 가공시 많이 쓰임
    // afterCompletion    void   1. 컨트롤러 로직 실행 된 후 호출 됨 2. 컨트롤러 실행 도중이나 view 페이지 실행 도중 error 발생 해도  실행됨
    //  공통 Exception 처리 로직 작성시 많이 쓰임
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(logingInterceptor()).addPathPatterns("/**");       //.includePathPatterns("/**") .excludePathPatterns("/**/*.ecxld");
//    }
//
//    @Bean
//    public HandlerInterceptor logingInterceptor() {
//        return new HandlerInterceptorAdapter() {
//            @Override
//            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//                String body="";
//                try {
//                    body = IOUtils.toString(new InputStreamReader(request.getInputStream()));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                String param = request.getParameterMap().entrySet().stream().map(it->it.getKey()+"="+ String.join(", ", Arrays.asList(it.getValue()))).collect( Collectors.joining( "&" ) );
//                log.info("request|"+request.getHeader(headerName)+"|"+request.getRequestURI()+"|"+param+"|"+body);
//                return true;
//            }
////            @Override
////            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
////            }
//        };
//    }



    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new HandlerMethodArgumentResolver() {
            @Override
            public boolean supportsParameter(MethodParameter parameter) {
                return OmnifitHeader.class.isAssignableFrom(parameter.getParameterType());
            }

            @Override
            public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
                String json = webRequest.getHeader(headerName);
                OmnifitHeader rval = null;
                if(json==null || json.isEmpty()){
                    if(parameter.hasParameterAnnotation(Valid.class) || parameter.hasParameterAnnotation(Validated.class))
                        throw  new ErrorMsgException(MsgCode.E20001, HttpStatus.BAD_REQUEST);
                    else
                        return new OmnifitHeader();
                } else {
                    try {
                        rval = new ObjectMapper().readValue(json, OmnifitHeader.class);
                        if (parameter.hasParameterAnnotation(Valid.class) || parameter.hasParameterAnnotation(Validated.class)) {
                            WebDataBinder binder = binderFactory.createBinder(webRequest, rval, parameter.getParameterName());
                            this.validateIfApplicable(binder, parameter);
                            if (binder.getBindingResult().hasErrors()) {
                                throw new BindException(binder.getBindingResult());
                            }
                        }
                    } catch (BindException e) {
                        throw e;
                    } catch (Exception e) {
                        throw new ErrorMsgException(new Error(MsgCode.E20002), e);
                    }
                }
                if (!StringUtils.isEmpty(rval.getCponId()) && !StringUtils.isEmpty(rval.getSerialNo())) {
                    // 2018-01-17 : header cponId + serialNo 값이 DB에 존재하는지 체크하는 로직 주석 처리 함. (성능 이슈 고려)
                    //rval = userService.headerToUserAndDevMerge(rval);
                }
                return rval;
            }

            protected void validateIfApplicable(WebDataBinder binder, MethodParameter methodParam) {
                Annotation[] annotations = methodParam.getParameterAnnotations();
                for (Annotation ann : annotations) {
                    Validated validatedAnn = AnnotationUtils.getAnnotation(ann, Validated.class);
                    if (validatedAnn != null || ann.annotationType().getSimpleName().startsWith("Valid")) {
                        Object hints = (validatedAnn != null ? validatedAnn.value() : AnnotationUtils.getValue(ann));
                        Object[] validationHints = (hints instanceof Object[] ? (Object[]) hints : new Object[]{hints});
                        binder.validate(validationHints);
                        break;
                    }
                }
            }
        });
    }
    //filter
    @Bean
    public FilterRegistrationBean commonsRequestLoggingFilter() {
//        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
//        filter.setIncludeQueryString(true);
//        filter.setIncludePayload(true);
//        filter.setMaxPayloadLength(10000);
//        filter.setIncludeHeaders(false);
//        filter.setAfterMessagePrefix("REQUEST DATA : ");

//        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
//        filter.setIncludeClientInfo(true);
//        filter.setIncludeQueryString(true);
//        filter.setIncludePayload(true);
//        LogRequestFilter filter = new LogRequestFilter();
        RequestLoggingFilter filter = new RequestLoggingFilter();
//        RequestDumperFilter filter = new RequestDumperFilter();
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(filter);
        registrationBean.setOrder(5);
        return registrationBean;
    }



    //리소스 패스 설정
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**")	            .addResourceLocations("/assets/");
        registry.addResourceHandler("/static/**")	            .addResourceLocations("/static/");
        registry.addResourceHandler("/resource/**")	            .addResourceLocations("/resource/");
        registry.addResourceHandler("/*.map")	                .addResourceLocations("/");
        registry.addResourceHandler("/*.js")	                .addResourceLocations("/");
        registry.addResourceHandler("/resources/**")            .addResourceLocations("/WEB-INF/resources/");
        registry.addResourceHandler("swagger-ui.html")          .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")              .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false).
                favorParameter(true).
                defaultContentType(MediaType.APPLICATION_JSON);
    }
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.favorPathExtension(false);
//        configurer.ignoreAcceptHeader(true);
//        configurer.defaultContentType(MediaType.APPLICATION_JSON);
//    }


}
