package com.tvd12.kotlin.examples.redis.entity

import com.tvd12.ezydata.database.annotation.EzyCachedKey
import com.tvd12.ezydata.database.annotation.EzyCachedValue
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@EzyCachedValue
data class Book(
    @EzyCachedKey
    var id: Long,
    var categoryId: Long,
    var authorId: Long,
    var name: String,
    var price: BigDecimal,
    var releaseDate: LocalDate,
    var releaseTime: LocalDateTime
)