package com.eureka.doumi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

//接口文档
@Configuration
public class SwaggerConfig {
    public ApiInfo getApiInfo(){
        return new ApiInfoBuilder()
                .title("斗米后台模板")
                .description("斗米兼职")
                .version("1.0")
                .build();
    }

    @Bean
    public Docket getDocket(){
        System.out.println("初始化swagger2===================>");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eureka.doumi"))
                .paths(PathSelectors.any())
                .build();
    }
}
