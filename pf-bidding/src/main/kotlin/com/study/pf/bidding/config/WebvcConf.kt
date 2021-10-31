package com.study.pf.bidding.config

import com.study.pf.bidding.common.interceptor.HttpInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebvcConf(
   private val httpInterceptor: HttpInterceptor
): WebMvcConfigurer {

}