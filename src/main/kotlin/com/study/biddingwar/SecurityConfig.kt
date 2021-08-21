package com.study.biddingwar

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig(val userService: UserService) : WebSecurityConfigurerAdapter() {

//    override fun configure(web: WebSecurity) {
//        web.ignoring().antMatchers("/css/**", "/js/**")
//    }

    @Bean
    override fun authenticationManagerBean() = super.authenticationManagerBean()!!

    override fun configure(http: HttpSecurity) {
//        http.authorizeRequests()
//                .antMatchers("/login", "/signup", "/user").permitAll()
//                .antMatchers("/").hasRole("USER")
//                .anyRequest().authenticated()
//            .and()
//                .formLogin().loginPage("/login").defaultSuccessUrl("/")
//            .and()
//                .logout().logoutSuccessUrl("/login").invalidateHttpSession(true)
        http.formLogin()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userService).passwordEncoder(BCryptPasswordEncoder())
    }

}
