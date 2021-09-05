package com.example.biddingwar.dto

import com.example.biddingwar.entity.Item
import java.time.LocalDateTime
import javax.servlet.http.HttpSession

class ItemCreateForm(val productName: String,
                     val title: String, val content:String,
                     val price: Int, val createTime:LocalDateTime? = null) {

    override fun toString(): String{
        return "ItemForm{\n" +
                "\tproductName: " + this.productName + "\n" +
                "\ttitle: " + this.title + "\n" +
                "\tcontent: " + this.content + "\n" +
                "\tprice: " + this.price + "\n}"
    }

    fun toEntity(userId: String): Item {
        return if (createTime == null) {
            Item(null, productName, title, content, price, userId)
        } else{
            Item(null, productName, title, content, price, userId, createTime)
        }
    }
}