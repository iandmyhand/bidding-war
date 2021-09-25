package com.study.biddingwar

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
class ItemControllerTest {

    @TestConfiguration
    class ItemControllerTestConfig {
        @Bean
        fun service() = mockk<ItemService>()
    }

    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var service: ItemService

    private val mapper = jacksonObjectMapper()
        .registerModule(JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

    @Test
    fun `상품 목록 조회`() {

        val itemList = listOf(
            Item(
                id=1,
                seller="정빈",
                title="우리집 나노의 스크레처",
                initialPrice=100000,
                category="생활용품",
                description="우리집 나노의 최애 스크레처"
            ),
        )

        every { service.list() } returns itemList

        val serialized = mapper.writeValueAsString(itemList)

        mvc.perform(get("/item"))
            .andExpect(status().isOk)
            .andExpect(content().json(serialized))
    }
}
