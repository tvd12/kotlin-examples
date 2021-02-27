package com.tvd12.kotlin.examples.redis.entity

import com.tvd12.ezydata.database.annotation.EzyCachedKey

@EzyCachedKey
data class BookNameAndAuthorId(
    var bookName: String,
    var authorId: Long
)
