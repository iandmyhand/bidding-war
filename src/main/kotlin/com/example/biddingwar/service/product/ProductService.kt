package com.example.biddingwar.service.product

import com.example.biddingwar.database.Bid
import com.example.biddingwar.database.Product
import com.example.biddingwar.repository.BidRepository
import com.example.biddingwar.repository.ProductRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.transaction.Transactional

@Service
@Transactional
class ProductService(val repository: ProductRepository, val bidRepository: BidRepository) {

    fun getAll(): ResponseEntity<MutableIterable<Product>> = ResponseEntity.ok().body(repository.findAll())

    fun get(id: Long): Optional<Product> = repository.findById(id)

    fun save(product: Product, request: HttpServletRequest): ResponseEntity<Product> {
        repository.save(product)
        return ResponseEntity.status(HttpStatus.CREATED).body(product)
    }

    fun delete(id: Long): ResponseEntity<String> {
        val productToDelete = repository.existsById(id)

        if (productToDelete) {
            repository.deleteById(id)
            return ResponseEntity.ok().body("DELETED")
        }

        return ResponseEntity.badRequest().body("DELETED_FAILED")
    }

    fun sell(productId: Long, request: HttpServletRequest): Product {

        val product: Product = repository.findById(productId).get()

        if (request.session.getAttribute("session") != product.userId) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Product must be sold by same user")
        }

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
