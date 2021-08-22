package com.peoplefund.biddingwar

import com.peoplefund.biddingwar.product.Product
import com.peoplefund.biddingwar.product.ProductRepository
import com.peoplefund.biddingwar.product.UserService
import com.peoplefund.biddingwar.product.UserSignupRequest
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component


@Configuration
class WebConfig: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .cors().disable()
            .csrf().disable()
            .formLogin().disable()
            .headers().frameOptions().disable()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}


@Component
@Profile("!test")
class DataLoaderConfig(
    private val productRepository: ProductRepository,
    private val userService: UserService,
) : CommandLineRunner {
    @Throws(Exception::class)
    override fun run(vararg args: String) {
        productRepository.save(Product("아파트 담보(의정부시 의정부동) 1111", 1000000L))
        productRepository.save(Product("아파트 담보(서울시 강남구) 2233", 5000000L))
        productRepository.save(Product("개인 채권 3234", 7000000L))

        userService.signUp(UserSignupRequest("test1", "1234qwer"))
    }
}
