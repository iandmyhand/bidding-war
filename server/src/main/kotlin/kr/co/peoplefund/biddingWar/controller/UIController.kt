package kr.co.peoplefund.biddingWar.controller

import kr.co.peoplefund.biddingWar.service.ProductService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/ui")
class UIController(val productService: ProductService) {

    @GetMapping
    fun list(model: Model): String {
        model["items"] = productService.list()
        return "list"
    }
}