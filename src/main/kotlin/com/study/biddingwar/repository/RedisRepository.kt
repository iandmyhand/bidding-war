package com.study.biddingwar.repository

interface RedisRepository{
    fun get(key:String): Any?

    fun <T>put(key:String, DATA:T)

    fun delete(key:String)

    fun expire(key:String)
}