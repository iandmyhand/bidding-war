package com.study.peoplefund.service

import com.study.peoplefund.domain.repository.ProductRepository
import com.study.peoplefund.web.dto.ProductRequest
import com.study.peoplefund.web.dto.ProductResponse
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