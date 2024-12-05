package com.fq.superparking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
* Swagger配置类
* */
@Configuration
@EnableSwagger2 //启动swagger
public class Knife4jConfig {
    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        String groupName = "3.X版本";
        Docket docket = new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .title("智能停车场系统")
                        .description("# 智能停车场系统所有的接口的入参，出参等等信息")
                        .termsOfServiceUrl("http://localhost:8081")
                        .contact(new Contact("fq", "http://localhost:8081", "1234567@qq.com"))
                        .version("3.0")
                        .build())
                //分组名称
                .groupName(groupName)
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.fq.superparking.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
