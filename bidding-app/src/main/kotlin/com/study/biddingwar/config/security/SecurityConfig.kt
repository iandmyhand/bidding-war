package com.study.biddingwar.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.*

@Configuration
@EnableWebSecurity
class SecurityConfig(private val customAuthEntryPoint: CustomAuthEntryPoint,
                     private val authProvider: CustomAuthProvider,
                     private val authSuccessHandler: AuthSuccessHandler): WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {

        http
            .exceptionHandling()
                .authenticationEntryPoint(customAuthEntryPoint)
            .and()
            .formLogin().permitAll()
                .usernameParameter("user_name")
                .loginProcessingUrl("/v1/auth/signin")
                .successHandler(authSuccessHandler)
                .failureHandler(authFailHandler())
            .and()
            .authorizeRequests {
                //일단 모든 path 허용
                authReq->
                authReq.antMatchers("/v1/user/signup").permitAll()
                authReq.anyRequest().authenticated()
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

    override fun configure(webSecurity: WebSecurity) {
        webSecurity.ignoring().antMatchers(
//            "/configuration/ui",
//            "/swagger-resources/**",
//            "/configuration/security",
//            "/webjars/**",
//            "/swagger/**",
            //spring-doc lib에서 swagger ui ignore할 목록
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
        )
    }


    @Bean
    fun authFailHandler():AuthenticationFailureHandler{
        return AuthFailHandler()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {

        val configuration = CorsConfiguration()
        configuration.allowedOrigins = Arrays.asList("http://localhost:3000")
        configuration.allowedMethods =
            Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
        configuration.allowedHeaders = Arrays.asList("*")
        configuration.allowCredentials = true
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}