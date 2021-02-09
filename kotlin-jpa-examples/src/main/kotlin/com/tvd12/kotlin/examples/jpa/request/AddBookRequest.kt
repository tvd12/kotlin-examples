package com.tvd12.kotlin.examples.jpa.request

import java.math.BigDecimal
import java.util.*

class AddBookRequest {
    var categoryId: Long = 0L
    var authorId: Long = 0L
    lateinit var bookName: String
    lateinit var price: BigDecimal
    lateinit var releaseDate: Date
    lateinit var releaseTime: Date
}