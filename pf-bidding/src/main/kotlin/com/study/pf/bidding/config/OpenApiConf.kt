package com.study.pf.bidding.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springdoc.core.GroupedOpenApi
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConf {

    @Bean
    fun goodsOpenApi(): GroupedOpenApi {
        val path = arrayOf("/goods/**")
        return GroupedOpenApi.builder()
            .group("goods")
            .pathsToMatch(*path)
            .build()
    }

    @Bean
    fun userOpenApi(): GroupedOpenApi {
        val path = arrayOf("/v1/user/**")
        return GroupedOpenApi.builder()
            .group("user")
            .pathsToMatch(*path)
            .build()
    }
}