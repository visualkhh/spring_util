package com.clone.chat.config;

import com.clone.chat.model.UserToken;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${project.properties.swagger-host}")
    private String swaggerHost;

    @Autowired
    ServerProperties serverProperties;
    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket api(TypeResolver typeResolver) {
        return new Docket(DocumentationType.SWAGGER_2)
                .host(swaggerHost)
                .apiInfo(apiInfo())
//                .ignoredParameterTypes(MedicineHeader.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.clone.chat.controller.api"))
                .paths(PathSelectors.any())

//                .paths(Predicates.not(PathSelectors.regex("/basic-error-controller.*")))
                .build().ignoredParameterTypes(UserToken.class)
//                .additionalModels(typeResolver.resolve(Msg.class, Error.class))

//                .additionalModels(typeResolver.resolve(OsType.class))
//                .globalOperationParameters(Arrays.asList(cponId, serialNo, cponModel, osTypeCd, osVersion, pkgNm, pkgVersion, userSeq))
//                .globalResponseMessage(RequestMethod.GET, getCustomizedResponseMessages())
//                .globalResponseMessage(RequestMethod.POST, getCustomizedResponseMessages())
//                .globalResponseMessage(RequestMethod.DELETE, getCustomizedResponseMessages())
//                .globalResponseMessage(RequestMethod.PUT, getCustomizedResponseMessages())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("chat-api")
                .description("chat-api documents")
                .build();

    }

    private List<ResponseMessage> getCustomizedResponseMessages() {
        ModelRef modelRef = new ModelRef(Error.class.getSimpleName());
        List<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseMessageBuilder().code(500).message("Server has crashed!! and Error").responseModel(modelRef).build());
        return responseMessages;
    }
}
