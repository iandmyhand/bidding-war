package com.example.biddingwar.product

import com.example.biddingwar.account.Account
import com.example.biddingwar.account.AccountRepository
import com.example.biddingwar.account.AccountService
import com.example.biddingwar.bidding.Bidding
import com.example.biddingwar.bidding.BiddingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

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
    fun createProduct(model: Model, @Valid product: Product, authentication: Authentication):String {
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
        val account: Account = accountService.getUserByUsername(authentication.name)
        service.getProduct(product_id).ifPresent {
            model.addAttribute("product", it)
            // 본인 상품은 본인만 입찰종료
            if (account == it.seller) {
                model.addAttribute("finish_bid", "finish_bid")
            }
        }
        model.addAttribute("biddings", biddingService.getBiddingByProduct(product_id))

        return "product"
    }

    @RequestMapping(value = ["/template/product/{product_id}"], method = [RequestMethod.POST])
    fun createBidding(
        @PathVariable("product_id") product_id: Long,
        model: Model,
        @Valid bidding: Bidding,
        authentication: Authentication
    ): String {
        service.getProduct(product_id).ifPresent { bidding.product = it }
        // 낙찰 후 입찰 방지
        if (biddingService.biddingFinished(product_id)){
            throw ResponseStatusException(HttpStatus.NOT_ACCEPTABLE)
        }
        val account: Account = accountService.getUserByUsername(authentication.name)
        bidding.account = account
        biddingService.save(bidding)
        service.getProduct(product_id).ifPresent { model.addAttribute("product", it) }
        model.addAttribute("biddings", biddingService.getBiddingByProduct(product_id))
        return "product"
    }

    @RequestMapping(value = ["/template/product/{product_id}/bid"], method = [RequestMethod.POST])
    fun finishBidding(
        @PathVariable("product_id") product_id: Long,
        model: Model,
        authentication: Authentication
    ): String {
        // 입찰 할 때 기존에 입찰한 금액보다 초과하는 금액만 입찰
        val highest_bidding = biddingService.getHighestBiddingByProduct(product_id)
        highest_bidding?.is_selected = true
        if (highest_bidding != null) {biddingService.save(highest_bidding)}

        service.getProduct(product_id).ifPresent { model.addAttribute("product", it) }
        model.addAttribute("biddings", biddingService.getBiddingByProduct(product_id))
        return "product"
    }
}