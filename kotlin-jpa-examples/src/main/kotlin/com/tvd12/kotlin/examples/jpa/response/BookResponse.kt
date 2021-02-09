package com.tvd12.kotlin.examples.jpa.response

import com.tvd12.kotlin.examples.jpa.entity.Author
import com.tvd12.kotlin.examples.jpa.entity.Category
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