package com.example.biddingwar.configure

import com.example.biddingwar.account.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
class SecurityConfig(@Autowired private val accountService: AccountService,
                     @Autowired private val passwordEncoder: PasswordEncoder): WebSecurityConfigurerAdapter() {

    companion object {
        const val LOGIN_SUCCESS_URL: String = "/view/success"
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .userDetailsService(accountService)
            .passwordEncoder(passwordEncoder)
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable().authorizeRequests()
            .antMatchers("/template/product").authenticated()
            .anyRequest().permitAll()
            .and()
            .formLogin()
            .successForwardUrl(LOGIN_SUCCESS_URL)
            .and()
            .authorizeRequests()

    }
}