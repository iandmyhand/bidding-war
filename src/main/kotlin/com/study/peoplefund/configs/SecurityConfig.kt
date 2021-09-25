package com.study.peoplefund.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.security.SecureRandom

@Configuration
class SecurityConfig {

    @Bean
    fun secureRandom(): SecureRandom {
        return SecureRandom()
    }
}