package com.example.bidding_war.config

import com.example.bidding_war.interceptor.PreMethodAuthInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableScheduling
class WebConfig(
    val preMethodAuthInterceptor: PreMethodAuthInterceptor
) : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(preMethodAuthInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/api/users/**")
            .excludePathPatterns("/api/users/signIn")
    }
}