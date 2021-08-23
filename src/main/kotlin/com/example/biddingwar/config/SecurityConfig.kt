package com.example.biddingwar.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter(){
    override fun configure(web: WebSecurity) {
        super.configure(web)
        web.ignoring()
            .antMatchers(
                "/h2-console/**",
                "/favicon.ico"
            )
    }

    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity
            .httpBasic().disable()
            .csrf().disable()

//            .authorizeRequests()
//            .antMatchers("/", "/user/**", "/welcome/**").permitAll()
//            .anyRequest().authenticated()
//
//            .and()
//            .formLogin()
//            .loginPage("/user/login") 	// 로그인 페이지 url
//            .usernameParameter("userId")  // 로그인 요청시 id용 파라미터 (메소드 이름이 usernameParameter로 무조건 써야하지만, 파라미터는 email이든 id이든 name이든 상관없다.)
//            .passwordParameter("userPw")
//            .loginProcessingUrl("/authenticate")
//            .failureForwardUrl("/user/login?error=1")
//            .defaultSuccessUrl("/",true)
//            .permitAll() // 로그인 성공시
//
//            .and()
//            .logout()
//            .logoutUrl("/user/logout")
//            .invalidateHttpSession(true)
//            .logoutSuccessUrl("/")
    }

//    @Bean
//    fun encoder() : PasswordEncoder{
//        return BCryptPasswordEncoder()
//    }
//
    // https://velog.io/@oyeon/Spring-Security%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EB%A1%9C%EA%B7%B8%EC%9D%B8
}