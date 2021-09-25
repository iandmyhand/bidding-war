package com.study.biddingwar.service

import com.study.biddingwar.controller.dto.ProductRequest
import com.study.biddingwar.controller.dto.ProductResponse
import com.study.biddingwar.domain.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(val productRepository: ProductRepository) {

    fun register(request: ProductRequest): Long {
        val product = request.toProduct()
        return productRepository.save(product).id!!
    }

    fun detail(id: Long): ProductResponse {
        return ProductResponse.of(productRepository.findById(id).orElseThrow())
    }

    fun list(): List<ProductResponse> {
        return ProductResponse.of(productRepository.findAll())
    }

}
