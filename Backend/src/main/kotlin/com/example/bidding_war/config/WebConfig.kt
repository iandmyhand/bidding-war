package com.example.bidding_war.config

import com.example.bidding_war.interceptor.PostMethodAuthInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    val postMethodAuthInterceptor: PostMethodAuthInterceptor
) : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(postMethodAuthInterceptor)
            .addPathPatterns("*")
            .excludePathPatterns("/api/users/")
    }
}