package com.tvd12.kotlin.examples.mongo.entity

import com.tvd12.ezydata.database.annotation.EzyCollection
import com.tvd12.ezyfox.annotation.EzyId
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@EzyCollection
data class Book(
    @EzyId
    var id: Long,
    var categoryId: Long,
    var authorId: Long,
    var name: String,
    var price: BigDecimal,
    var releaseDate: LocalDate,
    var releaseTime: LocalDateTime
)