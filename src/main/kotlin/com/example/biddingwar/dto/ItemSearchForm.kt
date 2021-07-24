package com.example.biddingwar.dto

class ItemSearchForm(val productName: String="") {

    override fun toString(): String{
        return "ItemForm{\n" +
                "\tproductName: " + this.productName + "\n}"
    }
}