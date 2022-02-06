package com.example.biddingwar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class BiddingWarApplication

fun main(args: Array<String>) {
	runApplication<BiddingWarApplication>(*args)
}
