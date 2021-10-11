package com.study.peoplefund.service

import com.study.peoplefund.domain.Bidding
import com.study.peoplefund.domain.Product
import com.study.peoplefund.domain.repository.BiddingRepository
import com.study.peoplefund.domain.repository.ProductRepository
import com.study.peoplefund.domain.repository.UserRepository
import com.study.peoplefund.domain.vo.BiddingStatus
import com.study.peoplefund.web.arguments.user.AuthInfo
import com.study.peoplefund.web.dto.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
    val productRepository: ProductRepository,
    val biddingRepository: BiddingRepository,
    val userRepository: UserRepository
) {

    @Transactional
    fun register(request: ProductRequest, sellerInfo: AuthInfo): Long {
        val product = Product(
            seller = userRepository.findById(sellerInfo.userId).orElseThrow(),
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
    fun bid(request: BidRequest, authInfo: AuthInfo): Long {
        val product = productRepository.findById(request.productId).orElseThrow()!!

        if (product.minPrice > request.price) {
            throw IllegalArgumentException("요청 가격은 최소 금액보다 작을 수 없습니다.")
        }

        if (biddingRepository.existsByPriceGreaterThanEqualAndProduct(request.price, product)) {
            throw IllegalArgumentException("요청 가격보다 같거나 큰 입찰 금액이 이미 존재합니다.")
        }

        if (product.seller.id == authInfo.userId) {
            throw IllegalArgumentException("판매자는 자신의 상품에 입찰할 수 없습니다.")
        }

        if (product.status != BiddingStatus.IN_PROGRESS) {
            throw IllegalArgumentException("진행 중이지 않은 상품에는 입찰할 수 없습니다.")
        }

        val bidding = biddingRepository.save(Bidding(
            product = product,
            price = request.price,
            user = userRepository.findById(authInfo.userId).orElseThrow()
        ))

        return bidding.id!!
    }

    @Transactional(readOnly = true)
    fun biddingListOfProduct(productId: Long): List<BiddingResponse> {
        val biddingList = biddingRepository.findByProductId(productId)

        return BiddingResponse.of(biddingList)
    }

    fun updateStatus(productId: Long, request: BiddingStatusRequest, authInfo: AuthInfo) {
        val product = productRepository.findById(productId)
            .orElseThrow { throw IllegalArgumentException("해당 상품이 존재하지 않습니다.") }

        if (request.getBiddingStatus() == BiddingStatus.SUCCESS) {
            validateIsExistsBidding(product)
            validateSellerIsUser(product.seller.id!!, authInfo.userId)

            product.status = BiddingStatus.SUCCESS
            productRepository.save(product)

            return
        }

        TODO("BiddingStatus에 따른 구현이 필요함")
    }

    private fun validateIsExistsBidding(product: Product) {
        if (!biddingRepository.existsByProductId(product.id!!)) {
            throw IllegalArgumentException("해당 상품의 입찰 내역이 존재하지 않습니다.")
        }
    }

    private fun validateSellerIsUser(sellerId: Long, userId: Long) {
        if (sellerId != userId) {
            throw IllegalArgumentException("요청한 사용자의 아이디와 상품 판매자의 아이디가 다릅니다.")
        }
    }
}