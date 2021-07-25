package com.example.biddingwar.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HelloController {
    @GetMapping("/")
    fun Init(model: Model): String{
        model["userName"] = "gwangho"
        model["content"] = "안녕하세요"

        return "greetings";
    }

    @GetMapping("/bye")
    fun Bye(model: Model): String{
        model["userName"] = "gwangho"
        model["content"] = "다음에 또 오세요"

        return "greetings";
    }
}