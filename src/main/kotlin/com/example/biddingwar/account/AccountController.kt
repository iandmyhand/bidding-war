package com.example.biddingwar.account
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@Controller
@RequestMapping("/account")
class AccountController (val service: AccountService,){

    @RequestMapping("/signin/success")
    fun success(request: HttpServletRequest): String {
        return "success"
    }

    @GetMapping("/signup")
    fun signUp():String {
        return "signup"
    }

    @PostMapping("/signup")
    fun signUp(model: Model, @Valid account: Account):String {
        service.saveAccount(account)
        return "success"
    }
}