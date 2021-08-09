package com.example.biddingwar.config

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter(){
//    override fun configure(web: WebSecurity) {
////        super.configure(web)
//        web.ignoring()
//            .antMatchers(
//                "/h2-console/**",
//                "/favicon.ico"
//            )
//    }

    override fun configure(http: HttpSecurity) {
//        super.configure(http)
        http.authorizeRequests()
            .antMatchers("/user/**").permitAll()
//            .and()
//            .formLogin()
//            .loginPage("/user/login")
//            .permitAll()


//            .antMatchers("/hello").permitAll()
//            .antMatchers("/login").permitAll()
//            .antMatchers("/h2-console").permitAll()
            .anyRequest().authenticated()
    }


}