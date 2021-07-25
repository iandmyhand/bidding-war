package com.peoplefund.biddingwar.product

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService(@Autowired val productRepository: ProductRepository) {
    fun list(searchCondition: SearchProductDto): List<Product> {

        if (searchCondition.name != null) {
            return productRepository.findByNameContains(searchCondition.name)
        }

        return productRepository.findAll().toList()
    }
}
