package com.example.biddingwar.config

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity as HttpSecurity1

@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter(){
    override fun configure(httpSecurity: HttpSecurity1) {
        httpSecurity.httpBasic().disable().csrf().disable()
    }
}