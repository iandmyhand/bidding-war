package com.peoplefund.biddingwar.common

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class CommonController {

    @GetMapping("/hello")
    fun hello(): String {
        return "hello"
    }
}
