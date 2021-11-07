package com.study.biddingwar.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.servers.Server
import org.springdoc.core.GroupedOpenApi
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@OpenAPIDefinition(
    info = Info(
        title = "bidding api service",
        version = "v1",
        description = "budding service api server swagger open-api-v3"
    )
)
@Configuration
class OpenApiConfig {

    @Bean
    fun openAPI(@Value("\${server.servlet.context-path}") contextPath: String): OpenAPI {
        return OpenAPI()
            .addServersItem(Server().url(contextPath))
    }

    /**
     * Essential : GroupedOpenApi Bean등록 안하면 전체 Controller api가 출력됨.
     */
    @Bean
    fun biddingOpenApi(): GroupedOpenApi? {
        val path: Array<String> = arrayOf("/v1/product/**","/v1/products/**", "/v1/user/**")
        return GroupedOpenApi.builder()
            .group("service-product-api")
            .pathsToMatch(*path)
            .build()
    }

}