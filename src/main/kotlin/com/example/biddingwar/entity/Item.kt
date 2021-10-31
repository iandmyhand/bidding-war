package com.example.biddingwar.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
class Item(
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
        @Column(name="user_id", length=20, unique=true)
        val userId: String,
        @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
        val bidTime: Date,
        @CreatedDate
        val createdTime: LocalDateTime = LocalDateTime.now(),
        @Column(name="status")
        var status: String = "입찰",
        )
{
        override fun toString(): String{
                return "Item{\n" +
                        "\tid: " + this.id + "\n" +
                        "\tproductName: " + this.productName + "\n" +
                        "\ttitle: " + this.title + "\n" +
                        "\tcontent: " + this.content + "\n" +
                        "\tprice: " + this.price + "\n" +
                        "\tcreate_time: " + this.createdTime + "\n" +
                        "\tuserId: " + this.userId + "\n" +
                        "}"
        }
}