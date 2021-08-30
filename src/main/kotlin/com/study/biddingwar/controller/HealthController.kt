package com.study.biddingwar.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health")
class HealthController {

    @GetMapping("/check")
    fun checkHealth(
    ): ResponseEntity<String> {
        return ResponseEntity.ok().body("pass..")
    }
}