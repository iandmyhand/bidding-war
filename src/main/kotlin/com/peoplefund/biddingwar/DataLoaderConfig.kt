package com.peoplefund.biddingwar

import com.peoplefund.biddingwar.product.ProductRepository
import org.springframework.boot.CommandLineRunner
import kotlin.Throws
import com.peoplefund.biddingwar.product.Product
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.lang.Exception

@Component
@Profile("!test")
class DataLoaderConfig(private val productRepository: ProductRepository) : CommandLineRunner {
    @Throws(Exception::class)
    override fun run(vararg args: String) {
        productRepository.save(Product("아파트 담보(의정부시 의정부동) 1111", 1000000L))
        productRepository.save(Product("아파트 담보(서울시 강남구) 2233", 5000000L))
        productRepository.save(Product("개인 채권 3234", 7000000L))
    }
}
