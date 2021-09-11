package com.example.biddingwar.entity

import lombok.Value
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Item(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null ,
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
        @CreatedDate
        val createdTime: LocalDateTime = LocalDateTime.now(),
        @Column(name="minPrice")
        var minPrice: Int = 0,
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