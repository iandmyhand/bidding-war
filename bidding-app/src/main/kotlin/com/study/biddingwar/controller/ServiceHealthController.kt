package com.study.biddingwar.controller

import com.study.biddingwar.service.SecuritySupportService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ServiceHealthController(private val securitySupportService: SecuritySupportService) {

    @GetMapping("/hello")
    fun getHealthCheck(): ResponseEntity<String> {
        return ResponseEntity.ok().body("service live")
    }

    @PostMapping("/refresh-rsakey")
    fun postRefreshRsaKey(): ResponseEntity<String> {
        securitySupportService.refreshRsaKeyCache()
        println("refresh rsa key")
        return ResponseEntity.ok().body("ok")
    }
}