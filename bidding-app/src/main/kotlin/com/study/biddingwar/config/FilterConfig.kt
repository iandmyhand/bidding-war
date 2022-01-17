package com.study.biddingwar.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.study.biddingwar.filter.DecryptFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class FilterConfig(private val objectMapper: ObjectMapper):WebMvcConfigurer{

    @Bean
    fun cryptoFilter(): FilterRegistrationBean<OncePerRequestFilter> {

        val cryptoFilterBean = FilterRegistrationBean<OncePerRequestFilter>(DecryptFilter(objectMapper))
        cryptoFilterBean.urlPatterns = arrayListOf(
            "/v1/product"
        )
        return cryptoFilterBean
    }
}