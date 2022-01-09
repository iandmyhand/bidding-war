package com.study.peoplefund

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class PeoplefundApplication

fun main(args: Array<String>) {
    runApplication<PeoplefundApplication>(*args)
}
