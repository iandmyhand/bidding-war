package com.study.peoplefund.service

import com.study.peoplefund.domain.Bidding
import com.study.peoplefund.domain.Product
import com.study.peoplefund.domain.repository.BiddingRepository
import com.study.peoplefund.domain.repository.ProductRepository
import com.study.peoplefund.domain.repository.UserRepository
import com.study.peoplefund.web.dto.BidRequest
import com.study.peoplefund.web.dto.BiddingResponse
import com.study.peoplefund.web.dto.ProductRequest
import com.study.peoplefund.web.dto.ProductResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
        val productRepository: ProductRepository,
        val biddingRepository: BiddingRepository,
        val userRepository: UserRepository
) {

    @Transactional
    fun register(request: ProductRequest, sellerId: Long): Long {
        val product = Product(
            seller = userRepository.findById(sellerId).orElseThrow(),
            name = request.name,
            minPrice = request.price
        )
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
        val product = productRepository.findById(request.productId).orElseThrow()!!

        if (product.minPrice > request.price) {
            throw IllegalArgumentException("요청 가격은 최소 금액보다 작을 수 없습니다.")
        }

        if (biddingRepository.existsByPriceGreaterThanEqual(request.price)) {
            throw IllegalArgumentException("요청 가격보다 큰 입찰 금액이 이미 존재합니다.")
        }

        if (product.seller.id == userId) {
            throw IllegalArgumentException("판매자는 자신의 상품에 입찰할 수 없습니다.")
        }

        val bidding = biddingRepository.save(Bidding(
            product = product,
            price = request.price,
            user = userRepository.findById(userId).orElseThrow()
        ))

        return bidding.id!!
    }

    @Transactional(readOnly = true)
    fun biddingListOfProduct(productId: Long): List<BiddingResponse> {
        val biddingList = biddingRepository.findByProductId(productId)

        return BiddingResponse.of(biddingList)
    }
}