package com.study.peoplefund.service

import com.study.peoplefund.domain.Bidding
import com.study.peoplefund.domain.repository.BiddingRepository
import com.study.peoplefund.domain.repository.ProductRepository
import com.study.peoplefund.domain.repository.UserRepository
import com.study.peoplefund.web.dto.BidRequest
import com.study.peoplefund.web.dto.ProductRequest
import com.study.peoplefund.web.dto.ProductResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException

@Service
class ProductService(
        val productRepository: ProductRepository,
        val biddingRepository: BiddingRepository,
        val userRepository: UserRepository
) {

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

    @Transactional
    fun bid(request: BidRequest, userId: Long): Long {
        val product = productRepository.findById(request.id).orElseThrow()!!

        if (product.firstOwnerId == userId){
            throw IllegalArgumentException("상품 등록자는 입찰에 참여할 수 없습니다")
        }

        if (biddingRepository.existsByPriceGreaterThanEqual(request.price)) {
            throw IllegalArgumentException("요청 가격보다 큰 입찰 금액이 이미 존재합니다.")
        }

        val bidding = biddingRepository.save(Bidding(
                product = product,
                price = request.price,
                user = userRepository.findById(userId).orElseThrow()
        ))

        return bidding.id!!
    }
}
