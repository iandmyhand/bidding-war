package com.study.biddingwar.config

import com.study.biddingwar.common.DecryptAnnotationFormatterFactory
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebMvcConfig: WebMvcConfigurer {
    override fun addFormatters(registry: FormatterRegistry) {
        registry.addFormatterForFieldAnnotation(DecryptAnnotationFormatterFactory())
    }
}
