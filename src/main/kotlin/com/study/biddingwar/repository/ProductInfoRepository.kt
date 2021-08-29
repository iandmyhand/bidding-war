package com.study.biddingwar.repository

import com.study.biddingwar.domain.entity.ProductInfo
import org.springframework.data.jpa.repository.JpaRepository

interface ProductInfoRepository:JpaRepository<ProductInfo, Long> {
}