package com.peoplefund.biddingwar.product

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class ProductService(@Autowired val productRepository: ProductRepository) {
    fun list(searchCondition: ProductSearch): List<Product> {

        if (searchCondition.name != null) {
            return productRepository.findByNameContains(searchCondition.name)
        }

        return productRepository.findAll().toList()
    }
}
