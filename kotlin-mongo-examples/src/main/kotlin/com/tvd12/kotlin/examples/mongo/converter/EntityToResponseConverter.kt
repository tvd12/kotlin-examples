package com.tvd12.kotlin.examples.mongo.converter

import com.tvd12.kotlin.examples.mongo.entity.Author
import com.tvd12.kotlin.examples.mongo.entity.Book
import com.tvd12.kotlin.examples.mongo.entity.Category
import com.tvd12.kotlin.examples.mongo.response.BookResponse
import com.tvd12.kotlin.examples.toDate

fun Book.toBookResponse(
    author: Author,
    category: Category
) =
    BookResponse(
        bookId = id,
        author = author,
        category = category,
        bookName = name,
        price = price,
        releaseTime = releaseTime.toDate()
    )

fun List<Book>.toBooksResponse(
    authors: Map<Long, Author>,
    categories: Map<Long, Category>
) =
    this.map {
        it.toBookResponse(
            author = authors.getValue(it.authorId),
            category = categories.getValue(it.categoryId)
        )
    }
