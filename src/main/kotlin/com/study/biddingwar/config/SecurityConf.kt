package com.study.biddingwar.config

import com.study.biddingwar.config.security.CustomAuthProvider
import com.study.biddingwar.config.security.LoginAuthFailureHandler
import com.study.biddingwar.config.security.LoginAuthSuccessHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import java.util.*

/**
 * JSP 때 처럼 View 페이지를 고려할 필요가 없음을 명심할 것
 */
@Configuration
@EnableWebSecurity
class SecurityConf: WebSecurityConfigurerAdapter() {
    companion object {
        const val SIGNIN_POINT: String = "/user/signin";
        const val SIGNIN_FAIL_POINT: String = "/user/signin/fail"; // fail..?
        const val SIGNOUT_POINT: String = "/user/signout";
    }

    @Autowired
    private lateinit var customAuthProvider: CustomAuthProvider

    override fun configure(http: HttpSecurity) {

        http
            .cors()
                .and()
            .csrf()
                .disable()
            .authorizeRequests()
                .antMatchers("/v1/**", "/health/**")
                    .permitAll()
                .anyRequest()
                    .authenticated()
                .and()
            .formLogin()
                .loginProcessingUrl(SIGNIN_POINT)
                    .permitAll()
                .failureHandler(LoginAuthFailureHandler())
                .successHandler(LoginAuthSuccessHandler())
                .usernameParameter("authId")
                .passwordParameter("authPassword")
            //  .failureForwardUrl(SIGNIN_FAIL_POINT)
                .and()
            .logout()
                .logoutUrl(SIGNOUT_POINT)
                .permitAll()
                .and()
            .exceptionHandling()
                .accessDeniedPage("/403")
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(customAuthProvider)
    }
}