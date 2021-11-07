package com.study.biddingwar

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull


@DataJpaTest
class ItemRepositoryTest {

//    @Autowired
//    lateinit var itemRepository: ItemRepository
//
//    @Test
//    fun `상품 저장 및 조회`() {
//        // Given
//        val item1 = Item(
//            title = "맥미니",
//            seller = "홍길동",
//            category = "PC",
//        )
//
//        // When
//        itemRepository.save(item1)
//        val foundItem = itemRepository.findByIdOrNull(1)
//
//        // Then
//        assertEquals(foundItem?.title, item1.title)
//        assertEquals(foundItem?.seller, item1.seller)
//        assertEquals(foundItem?.category, item1.category)
//    }
}