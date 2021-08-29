package com.study.biddingwar.config.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class SecurityConfig: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {

        http
            .authorizeHttpRequests {
                //일단 모든 path 허용
                authReq-> authReq.anyRequest().permitAll()
            }
            .formLogin()
            .disable()
            .csrf()
            .disable()
            .cors()
            .disable()
            .httpBasic()
            .disable()
    }
}