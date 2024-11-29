package org.example.config;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

@Configuration
public class SwaggerConfig {

    // OpenAPI类用于定制全局文档信息
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // 定制文档基本信息
                .info(new Info()
                        //关于文档信息
                        .title("考公面试系统API文档")
                        .description("该文档提供了考公面试系统的完整 API 接口说明，包括用户注册、登录、面试评估等功能。")
                        .version("1.0.0")
                        .termsOfService("https://example.com/terms")

                        //关于作者
                        .contact(new Contact()
                                .name("开发者:杨璐玮 蔡俊源 申世卓 罗垲霖 杜光晗")
                                .url("https://github.com/Software-Architecture-Project-Rush-Team/interview-back.git")
                                )

                        //关于许可证
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))

                //配置服务器信息
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("本地服务器"),
                        new Server().url("https://api.example.com").description("生产服务器")))

                //配置外部文档信息
                .externalDocs(new ExternalDocumentation()
                        .description("外部文档描述")
                        .url("https://example.com/docs"));
    }

    // GroupedOpenApi类用于API分组
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                // 修改为扫描 org.example.controller 包下的所有 Controller
                .packagesToScan("org.example.controller")
                .build();
    }

    // OpenApiCustomizer类用于自定义OpenAPI对象，比如全局添加头部信息。
    @Bean
    public OpenApiCustomizer customerGlobalHeaderOpenApiCustomizer() {
        return openApi -> openApi.info(
                new Info().title("自定义 API 文档")
                        .description("自定义 API 文档描述")
                        .version("1.0.0")
        );
    }
}
