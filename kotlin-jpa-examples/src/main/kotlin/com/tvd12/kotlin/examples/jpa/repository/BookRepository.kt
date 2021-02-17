package com.tvd12.kotlin.examples.jpa.repository

import com.tvd12.ezydata.database.EzyDatabaseRepository
import com.tvd12.ezydata.database.annotation.EzyQuery
import com.tvd12.ezyfox.annotation.EzyAutoImpl
import com.tvd12.ezyfox.util.Next
import com.tvd12.kotlin.examples.jpa.entity.Book
import com.tvd12.kotlin.examples.jpa.result.SumBookPriceResult

@EzyAutoImpl
interface BookRepository: EzyDatabaseRepository<Long, Book> {

    fun findByNameAndAuthorId(name: String, authorId: Long): Book?

    @EzyQuery("select e from Book e")
    fun findBooks(next: Next): List<Book>

    @EzyQuery("select e from Book e where e.name < ?0 order by e.name")
    fun findByNameLt(name: String, next: Next): List<Book>

    @EzyQuery("select e from Book e where e.name > ?0 order by e.name")
    fun findByNameGt(name: String, next: Next): List<Book>

    @EzyQuery("select sum(e.price) as sum from Book e", nativeQuery = true)
    fun sumPrice(): SumBookPriceResult
}