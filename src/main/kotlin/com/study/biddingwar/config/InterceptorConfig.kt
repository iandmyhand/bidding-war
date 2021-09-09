package com.study.biddingwar.config

import com.study.biddingwar.interceptor.CsrfTokenInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry

@Configuration
class InterceptorConfig(private val csrfTokenInterceptor: CsrfTokenInterceptor): WebMvcConfig() {

    val apiPattrn = listOf("/v1/product*")

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(csrfTokenInterceptor).addPathPatterns(apiPattrn)
        super.addInterceptors(registry)
    }

}