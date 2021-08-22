package com.peoplefund.biddingwar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession

@EnableRedisHttpSession
@SpringBootApplication
class BiddingWarApplication

fun main(args: Array<String>) {
    runApplication<BiddingWarApplication>(*args)
}
