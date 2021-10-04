package kr.co.peoplefund.biddingWar.service

import kr.co.peoplefund.biddingWar.controller.dto.ProductRequest
import kr.co.peoplefund.biddingWar.controller.dto.ProductResponse
import kr.co.peoplefund.biddingWar.domain.User
import kr.co.peoplefund.biddingWar.domain.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(val productRepository: ProductRepository) {

    fun register(owner: User, request: ProductRequest): Long {
        val product = request.toProduct(owner)
        return productRepository.save(product).id!!
    }

    fun detail(id: Long): ProductResponse {
        return ProductResponse.of(productRepository.findById(id).orElseThrow())
    }

    fun list(): List<ProductResponse> {
        return ProductResponse.of(productRepository.findAll())
    }

}