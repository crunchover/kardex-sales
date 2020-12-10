package com.pipecode.kardexsales.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String API_TITLE = "kardex-sales";
    public static final String API_DESCRIPTION = "Api que facilita la gestion del inventario para ventas a empleados";
    public static final String API_VERSION = "1.0";
    public static final String API_GROUP_NAME = "kardex-sales";


    @Bean
    public Docket documentation(ServletContext servletContext) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(API_GROUP_NAME).select()
                .apis(RequestHandlerSelectors.basePackage("com.pipecode.kardexsales.controller"))
                .paths(PathSelectors.any())
                .build()
                .pathProvider(new RelativePathProvider(servletContext))
                .select()
                .build();
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title(API_TITLE)
                .description(API_DESCRIPTION)
                .version(API_VERSION)
                .build();
    }
}
