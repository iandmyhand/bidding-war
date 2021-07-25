package com.study.biddingwar

import javax.persistence.* // Spring Data JPA(Java Persistance API)
import java.time.LocalDateTime
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Entity
data class Item(
    @Id @GeneratedValue val id: Long,
    val seller: String,
    val title: String,
    val initialPrice: Long,
    val category: String,
    val description: String,
    val status: String = "registered",
    val registered: LocalDateTime = LocalDateTime.now(),
    val finished: LocalDateTime?,
)

interface ItemRepository : CrudRepository<Item, Long> // <entity type, primary key type>

@Service
@Transactional
class ItemService(val repository: ItemRepository) {
    fun getAll() = repository.findAll()

    fun get(id: Long) = repository.findById(id)
    
    fun save(item: Item) = repository.save(item)
    
    fun delete(id: Long):  Boolean {
      val found = repository.existsById(id)
      
      if (found) {
        repository.deleteById(id)
      }
      
      return found
    }
  }