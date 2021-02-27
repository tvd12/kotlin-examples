package com.tvd12.kotlin.examples.redis.response

import com.tvd12.kotlin.examples.redis.entity.Author
import com.tvd12.kotlin.examples.redis.entity.Category
import java.math.BigDecimal
import java.util.*

data class BookResponse(
    val bookId: Long,
    val category: Category,
    val author: Author,
    val bookName: String,
    val price: BigDecimal,
    val releaseTime: Date
)