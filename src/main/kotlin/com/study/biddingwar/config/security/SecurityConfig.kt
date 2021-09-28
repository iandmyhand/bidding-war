package com.study.biddingwar.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.*

@Configuration
@EnableWebSecurity
class SecurityConfig(private val authProvider: CustomAuthProvider,
                     private val authSuccessHandler: AuthSuccessHandler): WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {

        http
            .formLogin().permitAll()
                .usernameParameter("user_name")
                .loginProcessingUrl("/v1/auth/signin")
                .successHandler(authSuccessHandler)
                .failureHandler(authFailHandler())
            .and()
            .authorizeRequests {
                //일단 모든 path 허용
                authReq-> authReq.anyRequest().permitAll()
            }
            .csrf()
            .disable()
            .cors().and()
            .httpBasic()
            .disable()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(authProvider)
    }


    @Bean
    fun authFailHandler():AuthenticationFailureHandler{
        return AuthFailHandler()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {

        val configuration = CorsConfiguration()
        configuration.allowedOrigins = Arrays.asList("*")
        configuration.allowedMethods =
            Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
        configuration.allowedHeaders = Arrays.asList("*")
        configuration.allowCredentials = false
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}