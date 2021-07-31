package com.study.biddingwar

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.boot.test.mock.mockito.MockBean
import org.mockito.BDDMockito.given
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
class ItemControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockBean
    lateinit var service: ItemService

    private val mapper = jacksonObjectMapper()
        .registerModule(JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

    @Test
    fun `상품 목록 조회`() {

        val item = Item(
            id=1,
            seller="seungwan",
            title="똥묻은변기",
            initialPrice=100000,
            category="house goods",
            description="아들의 똥이 묻은 하나뿐인 변기"
        )
        val itemList = listOf(item)

        given(service.list()).willReturn(itemList)

        val serialized = mapper.writeValueAsString(itemList)

        mockMvc.perform(get("/item"))
            .andExpect(status().isOk)
            .andExpect(content().json(serialized))
    }
}