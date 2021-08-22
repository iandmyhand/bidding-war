package com.peoplefund.biddingwar.product

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CrudRepository<Product, Long> {
    fun findByNameContains(name: String): List<Product>
}

@Repository
interface UserRepository: CrudRepository<User, Long> {
    fun existsUserByUserId(userId: String): Boolean
}