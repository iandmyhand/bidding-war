package com.example.biddingwar.entity

import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
open class Item(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @Column(name = "productName")
        var productName: String,
        @Column(name = "title")
        var title: String,
        @Column(name = "content")
        var content: String,
        @Column(name = "price")
        var price: Int,
        @CreatedDate
        val createdTime: LocalDateTime = LocalDateTime.now()
        )
{

        override fun toString(): String{
                return "Item{\n" +
                        "\tid: " + this.id + "\n" +
                        "\tproductName: " + this.productName + "\n" +
                        "\ttitle: " + this.title + "\n" +
                        "\tcontent: " + this.content + "\n" +
                        "\tprice: " + this.price + "\n" +
                        "\tcreate_time: " + this.createdTime + "\n"
        }
}