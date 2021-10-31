package com.study.pf.bidding.config

import com.study.pf.bidding.config.security.CustomAuthProvider
import com.study.pf.bidding.config.security.LoginAuthFailureHandler
import com.study.pf.bidding.config.security.LoginAuthSuccessHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.*

/**
 * JSP 때 처럼 View 페이지를 고려할 필요가 없음을 명심할 것
 */
@Configuration
@EnableWebSecurity
class SecurityConf(private val loginAuthFailureHandler: LoginAuthFailureHandler,
                   private val loginAuthSuccessHandler: LoginAuthSuccessHandler
): WebSecurityConfigurerAdapter() {
    companion object {
        const val SIGNIN_POINT: String = "/v1/user/signin";
        const val SIGNOUT_POINT: String = "/v1/user/signout";
    }

    @Autowired
    private lateinit var customAuthProvider: CustomAuthProvider

    override fun configure(http: HttpSecurity) {

        http
            .cors()
                .configurationSource(corsConfigurationSource())
                .and()
            .csrf()
                .disable()
            .authorizeRequests()
                .antMatchers("/v1/**", "/health/**", "/goods/**")
                    .permitAll()
                .anyRequest()
                    .authenticated()
                .and()
            .formLogin()
                .loginProcessingUrl(SIGNIN_POINT)
                    .permitAll()
                .failureHandler(loginAuthFailureHandler)
                .successHandler(loginAuthSuccessHandler)
                .usernameParameter("authId")
                .passwordParameter("authPassword")
                .and()
            .logout()
                .logoutUrl(SIGNOUT_POINT)
                .permitAll()
                .invalidateHttpSession(true)
                .and()
            .httpBasic()
                .disable()
            .exceptionHandling()
                .accessDeniedPage("/403")
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(customAuthProvider)
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration()
        // 일단 전체 허용
        corsConfiguration.allowedOrigins = Arrays.asList("*")
        corsConfiguration.allowedMethods = Arrays.asList("*")
        corsConfiguration.allowedHeaders = Arrays.asList("*")
        // corsConfiguration.allowCredentials = true --> front 개발로인해 주석처리

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfiguration)

        return source
    }
}