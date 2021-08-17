package com.study.peoplefund.service

import com.study.peoplefund.domain.repository.ProductRepository
import com.study.peoplefund.web.dto.ProductRequest
import com.study.peoplefund.web.dto.ProductResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(val productRepository: ProductRepository) {

    @Transactional
    fun register(request: ProductRequest): Long {
        val product = request.toProduct()
        return productRepository.save(product).id!!
    }

    @Transactional(readOnly = true)
    fun detail(id: Long): ProductResponse {
        return ProductResponse.of(productRepository.findById(id).orElseThrow())
    }

    @Transactional(readOnly = true)
    fun list(): List<ProductResponse> {
        return ProductResponse.of(productRepository.findAll())
    }
}