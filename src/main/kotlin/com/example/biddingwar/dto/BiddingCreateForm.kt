package com.example.biddingwar.dto

import com.example.biddingwar.entity.Bid

class BiddingCreateForm(val itemId: Long, val userId: Long, val price: Int) {

    override fun toString(): String{
        return "ItemForm{\n" +
                "\titemId: " + this.itemId + "\n" +
                "\tuserId: " + this.userId + "\n" +
                "\tprice: " + this.price + "\n" +
                "}"
    }

    fun toEntity(): Bid {
        return Bid(null, itemId, userId, price)
    }
    fun getUser(): Long{
        return userId;
    }
}