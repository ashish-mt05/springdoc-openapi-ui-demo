package com.ashish.config;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;


@Configuration
//@EnableSwagger2
public class SwaggerConfig {
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.ashish.*"))
//                .paths(PathSelectors.any())
//                .build();
//    }

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("mySecretHeader", new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .name("missingParam")))
                // AddSecurityItem section applies created scheme globally
                .addSecurityItem(new SecurityRequirement().addList("mySecretHeader"))
                .info(new Info()
                        .title("sample application API")
                        .version("v1")
                        .description("this is spring boot application")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                );
    }

    @Bean
    public OperationCustomizer customGlobalHeaders() {

        return (Operation operation, HandlerMethod handlerMethod) -> {

//            Parameter authorization = new Parameter().in(ParameterIn.HEADER.toString()).name("Authorization")
//                    .description("Authorization details JWT token")
//                    .schema(new StringSchema()).required(true);

            Parameter applicationId = new Parameter().in(ParameterIn.HEADER.toString()).schema(new StringSchema())
                    .name("Content-Type").description("Originating application or client using the service")
                    .required(false);

            //operation.addParametersItem(authorization);
            operation.addParametersItem(applicationId);

            return operation;
        };
    }
}
