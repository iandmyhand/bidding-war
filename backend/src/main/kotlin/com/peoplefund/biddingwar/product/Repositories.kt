package com.peoplefund.biddingwar.product

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductRepository : CrudRepository<Product, Long> {
    fun findByNameContains(name: String): List<Product>
}
