package com.cms.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringDoc配置类
 * 用于配置OpenAPI文档信息
 */
@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("课程管理系统API")
                        .description("课程管理系统后端API文档，包含用户管理、成绩管理等功能")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("严翊航")
                                .email("yan.yihang@example.com")
                                .url("http://localhost:8080"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}