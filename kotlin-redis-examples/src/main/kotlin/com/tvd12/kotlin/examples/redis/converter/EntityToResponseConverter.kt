package com.tvd12.kotlin.examples.redis.converter

import com.tvd12.kotlin.examples.redis.entity.Author
import com.tvd12.kotlin.examples.redis.entity.Book
import com.tvd12.kotlin.examples.redis.entity.Category
import com.tvd12.kotlin.examples.redis.response.BookResponse
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
