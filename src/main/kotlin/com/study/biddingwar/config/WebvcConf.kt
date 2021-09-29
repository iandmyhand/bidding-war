package com.study.biddingwar.config

import com.study.biddingwar.common.interceptor.HttpInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.servlet.http.HttpServletRequest

@Configuration
@EnableWebMvc
class WebvcConf(
   private val httpInterceptor: HttpInterceptor
): WebMvcConfigurer {

}