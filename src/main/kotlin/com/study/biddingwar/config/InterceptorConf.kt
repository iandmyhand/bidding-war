package com.study.biddingwar.config

import com.study.biddingwar.common.interceptor.LoginInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class InterceptorConf(private val loginInterceptor: LoginInterceptor): WebMvcConfigurer {

    val acceptPath = listOf("/**")
    val excludePath = listOf("/health/**")

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns(acceptPath)
                .excludePathPatterns(excludePath)
    }
}