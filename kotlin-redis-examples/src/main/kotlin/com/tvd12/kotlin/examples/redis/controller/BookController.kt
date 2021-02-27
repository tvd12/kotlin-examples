package com.tvd12.kotlin.examples.redis.controller

import com.tvd12.ezydata.redis.EzyRedisProxy
import com.tvd12.ezyhttp.core.exception.HttpBadRequestException
import com.tvd12.ezyhttp.core.exception.HttpNotFoundException
import com.tvd12.ezyhttp.server.core.annotation.*
import com.tvd12.kotlin.examples.redis.converter.toBookEntity
import com.tvd12.kotlin.examples.redis.converter.toBookResponse
import com.tvd12.kotlin.examples.redis.entity.Author
import com.tvd12.kotlin.examples.redis.entity.Book
import com.tvd12.kotlin.examples.redis.entity.BookNameAndAuthorId
import com.tvd12.kotlin.examples.redis.entity.Category
import com.tvd12.kotlin.examples.redis.request.AddBookRequest
import com.tvd12.kotlin.examples.redis.response.BookResponse

@Controller("/api/v1")
class BookController(
    redisProxy: EzyRedisProxy
) {

    private val idGentor = redisProxy.getAtomicLong("book")
    private val bookMap = redisProxy.getMap<Long, Book>("book")
    private val authorMap = redisProxy.getMap<Long, Author>("author")
    private val categoryMap = redisProxy.getMap<Long, Category>("category")
    private val bookIdByNameAndAuthorIdMap = redisProxy.getMap(
        "bookIdByNameAndAuthorId",
        BookNameAndAuthorId::class.java,
        Long::class.java
    )

    @DoPost("/book/add")
    fun addBook(@RequestBody request: AddBookRequest): BookResponse {
        val bookNameAndAuthorId = BookNameAndAuthorId(
            bookName = request.bookName,
            authorId = request.authorId
        )
        val existedBookId = bookIdByNameAndAuthorIdMap[bookNameAndAuthorId]
        if (existedBookId != null) {
            throw HttpBadRequestException(
                "author: ${request.authorId} has already registered book: ${request.bookName}"
            )
        }
        val author = authorMap[request.authorId]
            ?: throw HttpBadRequestException(
                "author: ${request.authorId} not found"
            )

        val category = categoryMap[request.categoryId]
            ?: throw HttpBadRequestException(
                "category: ${request.categoryId} not found"
            )

        val bookId = idGentor.incrementAndGet()
        val book = request.toBookEntity(bookId)
        bookMap[book.id] = book
        bookIdByNameAndAuthorIdMap[bookNameAndAuthorId] = bookId
        return book.toBookResponse(author, category)
    }

    @DoGet("/books/{bookId}")
    fun getBook(@PathVariable bookId: Long): BookResponse {
        val book = bookMap[bookId]
            ?: throw HttpNotFoundException("not found book with id: $bookId")
        val author = authorMap.getValue(book.authorId)
        val category = categoryMap.getValue(book.categoryId)
        return book.toBookResponse(
            author = author,
            category = category
        )
    }
}