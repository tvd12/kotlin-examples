package com.tvd12.kotlin.examples.jpa.controller

import com.tvd12.ezydata.database.repository.EzyMaxIdRepository
import com.tvd12.ezyhttp.server.core.annotation.Controller
import com.tvd12.ezyhttp.server.core.annotation.DoPost
import com.tvd12.ezyhttp.server.core.annotation.RequestBody
import com.tvd12.kotlin.examples.jpa.entity.Author
import com.tvd12.kotlin.examples.jpa.repository.AuthorRepository
import com.tvd12.kotlin.examples.jpa.request.AddAuthorRequest

@Controller("/api/v1/author")
class AuthorController(
    private val authorRepository: AuthorRepository,
    private val maxIdRepository: EzyMaxIdRepository
) {

    @DoPost("/add")
    fun addAuthor(@RequestBody request: AddAuthorRequest): Author {
        val author = Author(
            id = maxIdRepository.incrementAndGet("author"),
            name = request.authorName
        )
        authorRepository.save(author)
        return author
    }

}