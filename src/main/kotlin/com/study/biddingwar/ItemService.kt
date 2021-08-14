package com.study.biddingwar

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ItemService(val repository: ItemRepository) {
    fun list() = repository.findAll()

    fun get(id: Long) = repository.findByIdOrNull(id)

    fun save(item: Item) = repository.save(item)

    fun delete(id: Long):  Boolean {
      val found = repository.existsById(id)

      if (found) {
        repository.deleteById(id)
      }

      return found
    }
  }