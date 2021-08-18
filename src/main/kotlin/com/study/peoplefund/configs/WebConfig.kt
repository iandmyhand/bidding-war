package com.study.peoplefund.configs

import com.study.peoplefund.interceptor.PostMethodAuthInterceptor
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
            .allowedOrigins("http://localhost:3000")
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(postMethodAuthInterceptor)
            .addPathPatterns("/api/products")
    }
}
