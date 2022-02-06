package com.example.biddingwar.configure

import com.example.biddingwar.account.Account
import com.example.biddingwar.account.AccountRole
import com.example.biddingwar.account.AccountService
import com.example.biddingwar.product.Product
import com.example.biddingwar.product.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime

@Configuration
class BeanConfig {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    @Bean
    fun applicationRunner(): ApplicationRunner {
        return object : ApplicationRunner {

            @Autowired
            lateinit var accountService: AccountService
            @Autowired
            lateinit var productService: ProductService

            @Throws(Exception::class)
            override fun run(args: ApplicationArguments) {
                val admin = Account(null,
                    "admin@test.com",
                    "password",
                    mutableSetOf(AccountRole.ADMIN, AccountRole.USER))
                accountService.saveAccount(admin)

                val seller = Account(null,
                    "seller@test.com",
                    "password",
                    mutableSetOf(AccountRole.ADMIN, AccountRole.USER))
                accountService.saveAccount(seller)

                val product = Product(
                    1,
                    "initial product",
                    "initial product",
                    0,
                    10000,
                    seller,
                    biddingEndDateTime = LocalDateTime.now().plusMinutes(2)
                )
                productService.save(product)
            }
        }
    }
}