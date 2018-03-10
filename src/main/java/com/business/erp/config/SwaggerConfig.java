package com.business.erp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    /**
     * swaggerAPI
     *
     * @return swagger default setting
     */
    @Bean
    public Docket swaggerAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.business.erp.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    /**
     * @return api information
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Hyve Portal Mail Service")
                .description("JadenQin tuchuntong chaogang provide common erp API")
                .version("1.0.0").build();
    }

    /**
     * corsConfigurer
     *
     * @return WebMvcConfigurer
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/v2/api-docs*").allowedOrigins("*");
                registry.addMapping("/*").allowedOrigins("*");
            }
        };
    }
}
