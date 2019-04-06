package com.omnicns.omnifit2.api.config;

import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any()) // 현재 RequestMapping으로 할당된 모든 URL 리스트를 추출
//                .paths(PathSelectors.ant("/api/**")) // 그중 /api/** 인 URL들만 필터링
//                .build();
//    }
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.omnicns.omnifit2.api.controller.api"))
//                .paths(PathSelectors.any())
//                .build();
//
//    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sss.api.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false);
//                .globalResponseMessage(RequestMethod.GET,
//                        new ArrayList(new ResponseMessageBuilder()
//                                        .code(500)
//                                        .message("Internal Server Error")
//                                        .responseModel(new ModelRef("Error"))
//                                        .build(),
//                                new ResponseMessageBuilder()
//                                        .code(400)
//                                        .message("Bad Request")
//                                        .build(),
//                                new ResponseMessageBuilder()
//                                        .code(404)
//                                        .message("Not Found")
//                                        .build()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("test swagger2")
                .description("swagger2 사용해 봅시다.")
                .build();

    }
}