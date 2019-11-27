package com.menu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
//注解表示启用Swagger2
public class SwaggerConfig {
    @Bean
    //注解表示交由bean容器去管理
    public Docket menuSystemApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        // docket.groupName("创新中心-用户模块API");
        docket.enable(true);
        //apiInfo()用来创建该Api的基本信息（这些基本信息会展现在文档页面中）。
        //select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现，本例采用指定扫描的包路径来定义，Swagger会扫描该包下所有Controller定义的API，并产生文档内容（除了被@ApiIgnore指定的请求）。
        docket.apiInfo(apiInfo()).select() // 选择那些路径和api会生成document
            .apis(RequestHandlerSelectors.basePackage("com.menu.controller")) // 对所有api进行监控
            .paths(PathSelectors.any()).build(); // 对所有路径进行监控
        return docket;
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("格林豪泰小程序 API").description("适用本次系统API")
            .termsOfServiceUrl("").contact("---")
            .license("Version 1.0").licenseUrl("#").version("1.0")
            .build();
    }

}