package com.example.biddingwar

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class ProductTemplateController(val service: ProductService) {

    @RequestMapping(value=["/template/product"], method = [RequestMethod.GET])
    fun getProducts(model: Model):String {
        model.addAttribute("products", service.getAll())
        return "products"
    }

    @RequestMapping(value=["/template/product/{product_id}"], method = [RequestMethod.GET])
    fun getProduct(@PathVariable("product_id") product_id: Long, model: Model):String {
        service.getProduct(product_id).ifPresent {model.addAttribute("product", it)}
        return "product"
    }
}