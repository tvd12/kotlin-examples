package com.tvd12.kotlin.examples.redis.converter

import com.tvd12.kotlin.examples.redis.entity.Book
import com.tvd12.kotlin.examples.redis.request.AddBookRequest
import com.tvd12.kotlin.examples.toLocalDate
import com.tvd12.kotlin.examples.toLocalDateTime

fun AddBookRequest.toBookEntity(bookId: Long) =
    Book(
        id = bookId,
        categoryId = categoryId,
        authorId = authorId,
        name = bookName,
        price = price,
        releaseDate = releaseDate.toLocalDate(),
        releaseTime = releaseTime.toLocalDateTime()
    )