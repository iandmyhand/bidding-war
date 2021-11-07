package com.study.biddingwar.repository

interface RedisRepository{
    fun get(key:String): Any?

    fun getLock(key:String, value:String): Boolean

    fun <T>put(key:String, DATA:T)

    fun delete(key:String)

    fun expire(key:String)
}