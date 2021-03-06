package com.study.biddingwar

import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemServiceTest {

    @MockK
    private lateinit var repository: ItemRepository

    @InjectMockKs
    private lateinit var service: ItemService

    private lateinit var itemList: List<Item>

    @BeforeAll
    fun setUp() {
        MockKAnnotations.init(this)

        // Given
        itemList = listOf(
            Item(
                title = "맥미니",
                seller = "홍길동",
                category = "전자기기",
            ),
            Item(
                title = "맥북",
                seller = "김철수",
                category = "전자기기",
            ),
        )
    }

    @Test
    fun `상품 목록 조회`() {
        // Given
        every { repository.findAll() } returns itemList

        // When
        val foundItems = service.list()

        // Then
        Assertions.assertEquals(foundItems.size, 2)
    }
}