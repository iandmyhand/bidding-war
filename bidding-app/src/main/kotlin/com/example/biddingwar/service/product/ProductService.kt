package com.example.biddingwar.service.product

import com.example.biddingwar.database.Bid
import com.example.biddingwar.database.Product
import com.example.biddingwar.repository.BidRepository
import com.example.biddingwar.repository.ProductRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.Instant
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class ProductService(val repository: ProductRepository, val bidRepository: BidRepository) {

    fun getAll(): MutableIterable<Product> = repository.findAll()

    fun get(id: Long): Optional<Product> = repository.findById(id)

    fun save(product: Product): Product {
        product.biddingEndTime = Instant.now().plus(Duration.ofDays(1)) // 입찰기간을 하루로 지정
        repository.save(product)
        return product
    }

    fun delete(id: Long): Boolean {
        val productToDelete = repository.existsById(id)

        if (productToDelete) {
            repository.deleteById(id)
            return true
        }

        return false
    }

    fun sell(productId: Long): Product {

        val product: Product = repository.findById(productId).get()

        val bids: List<Bid>? = bidRepository.findByProductId(productId)

        val winningBid = bids?.maxByOrNull { it.biddingPrice }
        if (winningBid != null) {
            product.let {
                it.finalBidPrice = winningBid.biddingPrice
                it.isBidComplete = true
            }
        }

        return product
    }

    @Scheduled(cron = "0 * * * * *")
    fun finishBid() {
        val now: Instant = Instant.now()
        val finishedProducts: List<Product> = repository.findByIsBidCompleteFalseAndBiddingEndTimeLessThanEqual(now)

        for (finishedProduct in finishedProducts) {
            sell(finishedProduct.id)
        }
    }
}
