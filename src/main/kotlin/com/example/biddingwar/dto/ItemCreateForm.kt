package com.example.biddingwar.dto

import com.example.biddingwar.entity.Item
import java.text.SimpleDateFormat
import java.time.LocalDateTime

class ItemCreateForm(val productName: String,
                     val title: String, val content:String,
                     val price: Int, val bidTime: String,
                     val createTime:LocalDateTime? = null) {

    override fun toString(): String{
        return "ItemForm{\n" +
                "\tproductName: " + this.productName + "\n" +
                "\ttitle: " + this.title + "\n" +
                "\tcontent: " + this.content + "\n" +
                "\tprice: " + this.price + "\n}"
    }

    fun toEntity(userId: String): Item {
        val bidTiem = SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(this.bidTime)

        return if (createTime == null) {
            Item(null, productName, title, content, price, userId, bidTiem)
        } else{
            Item(null, productName, title, content, price, userId, bidTiem, createTime)
        }
    }
}