package com.study.biddingwar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [ RedisRepositoriesAutoConfiguration::class ])
class BiddingWarApplication

fun main(args: Array<String>) {
    runApplication<BiddingWarApplication>(*args)
}