package com.example.biddingwar.product

import com.example.biddingwar.bidding.BiddingRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class ProductService(val repository: ProductRepository,
                     val biddingRepository: BiddingRepository){

    fun getAll() = repository.findAll()

    fun getProduct(id: Long) = repository.findById(id)

    fun save(product: Product) = repository.save(product)

    @Scheduled(cron = "0 * * * * *")
    fun endBidding() {
        val notEndProducts = repository.findByBiddingEndDateTimeLessThanEqual(LocalDateTime.now())

        for (product in notEndProducts) {
            val highest_bidding = biddingRepository.findHighestBiddingByProductId(product.id)
            if (highest_bidding?.is_selected == true) continue
            highest_bidding?.is_selected = true
            if (highest_bidding != null) {biddingRepository.save(highest_bidding)}

        }
    }
}