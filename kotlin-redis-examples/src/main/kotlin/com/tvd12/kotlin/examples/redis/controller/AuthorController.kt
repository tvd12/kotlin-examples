package com.tvd12.kotlin.examples.redis.controller

import com.tvd12.ezydata.redis.EzyRedisProxy
import com.tvd12.ezyhttp.server.core.annotation.Controller
import com.tvd12.ezyhttp.server.core.annotation.DoPost
import com.tvd12.ezyhttp.server.core.annotation.RequestBody
import com.tvd12.kotlin.examples.redis.entity.Author
import com.tvd12.kotlin.examples.redis.request.AddAuthorRequest

@Controller("/api/v1/author")
class AuthorController(
    redisProxy: EzyRedisProxy
) {

    private val idGentor = redisProxy.getAtomicLong("author")
    private val authorMap = redisProxy.getMap<Long, Author>("author")

    @DoPost("/add")
    fun addAuthor(@RequestBody request: AddAuthorRequest): Author {
        val author = Author(
            id = idGentor.incrementAndGet(),
            name = request.authorName
        )
        authorMap[author.id] = author
        return author
    }

}