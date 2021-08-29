package com.study.biddingwar.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ServiceHealthController {

    @GetMapping("/hello")
    fun getHealthCheck(){
        ResponseEntity.ok().body("service live")
    }
}