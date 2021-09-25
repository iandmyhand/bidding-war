package com.study.biddingwar.domain.repository

import com.study.biddingwar.domain.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long?> {
}
