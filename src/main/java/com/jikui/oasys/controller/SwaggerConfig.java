package com.jikui.oasys.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: zhaojq
 * @Description:
 * @Date:Create：in 2020/6/21 12:24
 * @Modified By：
 **/
public class SwaggerConfig {
    @Configuration
    @EnableSwagger2
    public class Swagger2Config {
        @Bean
        public Docket createRestApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.zero.system.controller"))
                    .paths(PathSelectors.any())
                    .build();
        }

        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("积跬 OA")
                    .description("SpringBoot+LayUI+Thymeleaf")
                    .termsOfServiceUrl("http://127.0.0.1:80/")
                    .contact("赵坚强")
                    .version("1.0")
                    .build();
        }

    }
}
