package com.tvd12.kotlin.examples.mongo.controller

import com.tvd12.ezydata.database.repository.EzyMaxIdRepository
import com.tvd12.ezyfox.util.Next
import com.tvd12.ezyhttp.core.exception.HttpBadRequestException
import com.tvd12.ezyhttp.core.exception.HttpNotFoundException
import com.tvd12.ezyhttp.server.core.annotation.*
import com.tvd12.kotlin.examples.mongo.converter.toBookEntity
import com.tvd12.kotlin.examples.mongo.converter.toBookResponse
import com.tvd12.kotlin.examples.mongo.converter.toBooksResponse
import com.tvd12.kotlin.examples.mongo.entity.Book
import com.tvd12.kotlin.examples.mongo.repository.AuthorRepository
import com.tvd12.kotlin.examples.mongo.repository.BookRepository
import com.tvd12.kotlin.examples.mongo.repository.CategoryRepository
import com.tvd12.kotlin.examples.mongo.request.AddBookRequest
import com.tvd12.kotlin.examples.mongo.response.BookResponse
import java.math.BigDecimal

@Controller("/api/v1")
class BookController(
    private val authorRepository: AuthorRepository,
    private val bookRepository: BookRepository,
    private val categoryRepository: CategoryRepository,
    private val maxIdRepository: EzyMaxIdRepository
) {

    @DoPost("/book/add")
    fun addBook(@RequestBody request: AddBookRequest): BookResponse {
        val existedBook = bookRepository.findByNameAndAuthorId(
            name = request.bookName,
            authorId = request.authorId
        )
        if (existedBook != null) {
            throw HttpBadRequestException(
                "author: ${request.authorId} has already registered book: ${request.bookName}"
            )
        }
        val author = authorRepository.findById(request.authorId)
            ?: throw HttpBadRequestException(
                "author: ${request.authorId} not found"
            )

        val category = categoryRepository.findById(request.categoryId)
            ?: throw HttpBadRequestException(
                "category: ${request.categoryId} not found"
            )

        val bookId = maxIdRepository.incrementAndGet("book")
        val book = request.toBookEntity(bookId)
        bookRepository.save(book)
        return book.toBookResponse(author, category)
    }

    @DoGet("/books/{bookId}")
    fun getBook(@PathVariable bookId: Long): BookResponse {
        val book = bookRepository.findById(bookId)
            ?: throw HttpNotFoundException("not found book with id: $bookId")
        val author = authorRepository.findById(book.authorId)
        val category = categoryRepository.findById(book.categoryId)
        return book.toBookResponse(
            author = author,
            category = category
        )
    }

    @DoGet("/books")
    fun getBooks(
        @RequestParam("lower_than") lowerThan: String?,
        @RequestParam("upper_than") upperThan: String?,
        @RequestParam("size") size: Int
    ): List<BookResponse> {
        val books = when (upperThan.isNullOrEmpty()) {
            true -> when (lowerThan.isNullOrEmpty()) {
                true -> bookRepository.findBooks(Next.fromSkipLimit(0, size.toLong()))
                false -> bookRepository.findByNameLt(lowerThan, Next.fromSkipLimit(0, size.toLong()))
            }
            false -> bookRepository.findByNameGt(upperThan, Next.fromSkipLimit(0, size.toLong()))
        }
        val authorIds = books.map { it.authorId }
        val categoryIds = books.map { it.categoryId }
        val authors = authorRepository.findListByIds(authorIds)
            .map { it.id to it }
            .toMap()
        val categories = categoryRepository.findListByIds(categoryIds)
            .map { it.id to it }
            .toMap()
        return books.toBooksResponse(
            authors = authors,
            categories = categories
        )
    }

    @DoGet("/books/expected-revenue")
    fun getExpectedRevenue(): BigDecimal =
        bookRepository.sumPrice().sum
}