package com.example.biddingwar.dto

import com.example.biddingwar.entity.Item

class ItemCreateForm(val productName: String,
                     val title: String, val content:String,
                     val price: Int) {

    override fun toString(): String{
        return "ItemForm{\n" +
                "\tproductName: " + this.productName + "\n" +
                "\ttitle: " + this.title + "\n" +
                "\tcontent: " + this.content + "\n" +
                "\tprice: " + this.price + "\n}"
    }

    fun toEntity(): Item {
        return Item(null, productName, title, content, price)
    }
}