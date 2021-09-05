package com.example.biddingwar.repository

import com.example.biddingwar.entity.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface ItemRepository : JpaRepository<Item, Long> {
    fun findAllByProductNameContainingOrderByCreatedTimeDesc(productName: String): Iterable<Item>
    fun findItemById(id: Long) : Item?
}