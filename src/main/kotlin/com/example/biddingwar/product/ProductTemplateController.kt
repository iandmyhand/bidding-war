package com.example.biddingwar.product

import com.example.biddingwar.account.Account
import com.example.biddingwar.account.AccountRepository
import com.example.biddingwar.account.AccountService
import com.example.biddingwar.bidding.Bidding
import com.example.biddingwar.bidding.BiddingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class ProductTemplateController(val service: ProductService,
                                val biddingService: BiddingService,
                                val accountService: AccountService) {

    @RequestMapping(value=["/template/product"], method = [RequestMethod.GET])
    fun getProducts(model: Model):String {
        model.addAttribute("products", service.getAll())
        return "products"
    }

    @RequestMapping(value=["/template/product"], method = [RequestMethod.POST])
    fun createProduct(model: Model, product: Product, authentication: Authentication):String {
        val account: Account = accountService.getUserByUsername(authentication.name)
        product.seller = account
        service.save(product)
        model.addAttribute("products", service.getAll())
        return "products"
    }

    @RequestMapping(value=["/template/product/{product_id}"], method = [RequestMethod.GET])
    fun getProduct(
        @PathVariable("product_id") product_id: Long,
        model: Model,
        authentication: Authentication,
    ):String {
        service.getProduct(product_id).ifPresent {model.addAttribute("product", it)}
        model.addAttribute("biddings", biddingService.getBiddingByProduct(product_id))
        return "product"
    }

    @RequestMapping(value = ["/template/product/{product_id}"], method = [RequestMethod.POST])
    fun createBidding(
        @PathVariable("product_id") product_id: Long,
        model: Model,
        bidding: Bidding,
        authentication: Authentication
    ): String {
        service.getProduct(product_id).ifPresent { bidding.product = it }
        val account: Account = accountService.getUserByUsername(authentication.name)
        bidding.account = account
        biddingService.save(bidding)
        service.getProduct(product_id).ifPresent { model.addAttribute("product", it) }
        model.addAttribute("biddings", biddingService.getBiddingByProduct(product_id))
        return "product"
    }
}