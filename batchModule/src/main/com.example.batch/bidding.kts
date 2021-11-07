package com.example.batch.bidding
import com.example.biddingwar.repository.ItemRepository

import org.springframework.stereotype.Controller
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.beans.factory.annotation.Autowired

import java.util.*

@Controller
class biddingBatch(
    @Autowired val itemRepository: ItemRepository
    ) {
    @Scheduled(cron = "0/1 * * * * *")
    fun autoBidding() {
        val now = Date()
        val status = "입찰"
        var items = itemRepository.findAllByStatusAndBidTimeIsLessThanEqual(status, now)

        for(item in items){
            biddingItem(item)
        }
    }
}