package com.study.biddingwar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BiddingWarApplication

fun main(args: Array<String>) {
//    ElasticApmAttacher.attach()
    runApplication<BiddingWarApplication>(*args)
}
