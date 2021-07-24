package com.example.biddingwar.repository

import com.example.biddingwar.entity.Item
import org.springframework.data.repository.CrudRepository

interface ItemRepository : CrudRepository<Item, Long> {
    fun findAllByProductNameContainingOrderByCreatedTimeDesc(productName: String): Iterable<Item>
}