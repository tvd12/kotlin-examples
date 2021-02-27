package com.tvd12.kotlin.examples.redis.entity

import com.tvd12.ezydata.database.annotation.EzyCachedKey
import com.tvd12.ezydata.database.annotation.EzyCachedValue

@EzyCachedValue
data class Author(
    @EzyCachedKey
    var id: Long,
    var name: String
)