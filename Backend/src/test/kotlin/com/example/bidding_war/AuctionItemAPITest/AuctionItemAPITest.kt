package com.example.bidding_war.AuctionItemAPITest

import com.example.bidding_war.model.AuctionItem
import com.example.bidding_war.repository.AuctionItemRepository
import com.example.bidding_war.service.AuctionItemService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class AuctionItemAPITest @Autowired constructor(
    auctionItemRepository: AuctionItemRepository,
) {
    val auctionItemService = AuctionItemService(auctionItemRepository)

    @Test
    fun `경매 물품 단건 조회`() {

        val macBook = AuctionItem(
            title = "맥북", owner = "건식", description = "회사 맥북", startPrice = 1300000, minBiddingPrice = 100000
        )

        auctionItemService.register(macBook)
        val result = auctionItemService.findById(1).get()
        assertThat(result)
            .extracting(
                AuctionItem::id,
                AuctionItem::title,
                AuctionItem::owner,
                AuctionItem::description,
                AuctionItem::startPrice,
                AuctionItem::minBiddingPrice,
            )
            .isEqualTo(
                listOf(
                    1L, "맥북", "건식", "회사 맥북", 1300000L, 100000L
                )
            )
    }

    @Test
    fun `경매 물품 전체 조회`() {

        val Iphone = AuctionItem(
            title = "아이폰", owner = "건식", description = "쓴지 1년됐음", startPrice = 800000, minBiddingPrice = 10000
        )

        auctionItemService.register(Iphone)

        val result = auctionItemService.findAll()
        assertThat(result).hasSize(2)
    }

}