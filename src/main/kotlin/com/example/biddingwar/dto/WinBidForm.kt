package com.example.biddingwar.dto

class WinBidForm(val itemId: Long, val userId: String) {

    override fun toString(): String{
        return "ItemForm{\n" +
                "\titemId: " + this.itemId + "\n" +
                "\tuserId: " + this.userId + "\n" +
                "}"
    }

    fun getItem(): Long {
        return this.itemId
    }
    fun getUser(): String {
        return this.userId
    }
}