package com.example.biddingwar.repository

import com.example.biddingwar.database.Product
import org.springframework.data.repository.CrudRepository

interface ProductRepository: CrudRepository<Product, Long> {
}