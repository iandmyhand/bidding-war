package com.example.biddingwar.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class HelloController {
    @GetMapping("/", "/welcome")
    fun init(model: Model): String{
        return "redirect:/welcome/hello/";
    }

    @GetMapping("/welcome/hello")
    fun hello(model: Model): String{
        model["userName"] = "고객"
        model["content"] = "환영합니다"

        return "greetings";
    }

    @GetMapping("/welcome/bye")
    fun bye(model: Model): String{
        model["userName"] = "고객"
        model["content"] = "다음에 뵙겠습니다"

        return "greetings";
    }
}