package com.study.biddingwar.controller

import com.study.biddingwar.common.util.SessionUtil
import com.study.biddingwar.domain.dto.SessionDto
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
        /*val sessionDto = SessionDto(
                sessionId = "default:",
                remoteIpNo = 2,
                userId = "user!!.userId",
                userName = "user.userName",
                userNick = "user.userNick"
        )

        SessionUtil.setSessionDto(sessionDto)*/

        return ResponseEntity.ok().body("pass..")
    }
}