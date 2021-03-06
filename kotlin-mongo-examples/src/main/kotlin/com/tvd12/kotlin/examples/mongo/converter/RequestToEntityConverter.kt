package com.tvd12.kotlin.examples.mongo.converter

import com.tvd12.kotlin.examples.mongo.entity.Book
import com.tvd12.kotlin.examples.mongo.request.AddBookRequest
import com.tvd12.kotlin.examples.toLocalDate
import com.tvd12.kotlin.examples.toLocalDateTime
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.*

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