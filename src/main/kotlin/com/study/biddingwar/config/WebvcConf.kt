package com.study.biddingwar.config

import com.study.biddingwar.common.interceptor.HttpInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebvcConf(
   private val httpInterceptor: HttpInterceptor
): WebMvcConfigurer {

    //@TODO : Interceptor, local, message 처리 등...
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(httpInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/health/**")
    }
}