package com.example.biddingwar.account

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("/view")
class AccountController {

    @RequestMapping("/success")
    fun success(request: HttpServletRequest): String {
        return "success"
    }
}