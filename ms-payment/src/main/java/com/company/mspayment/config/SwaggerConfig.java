package com.company.mspayment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("az.company.mspayment"))
                .paths(PathSelectors.any())
                .build();
    }

}