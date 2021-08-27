package kr.co.peoplefund.biddingWar.controller.dto


class HackResponse (
    val token: String,
    val userId: Long,
    val products: List<ProductResponse>
)
