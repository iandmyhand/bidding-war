package com.example.biddingwar.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    fun api(): Docket {
        val version = "V1";
        val title = "REST API ";
        return Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .groupName(version)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example.biddingwar")) //
            .paths(PathSelectors.any())
            .build()
//            .apiInfo(apiInfo(version, title))
    }

    private fun apiInfo(version: String, title: String): ApiInfo {
        return ApiInfo(
            title,
            "통합 API Docs",
            version,
            "www.example.com",
            "FKgk",
            "Licenses",
            "www.example.com"
        )

    }
}
