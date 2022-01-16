package com.study.biddingwar.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/test/call")
    fun getTestCall(): ResponseEntity<Map<String, String>> {

        return ResponseEntity.ok().body(mapOf("key" to "test call"))
    }

    @GetMapping("/test/call2")
    fun getTestCall2(): ResponseEntity<Map<String, String>> {
        return ResponseEntity.ok().body(mapOf("key" to "test call2"))
    }
}