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

    @EzyQuery("{\$orderby:{name:1}}")
    fun findBooks(next: Next): List<Book>

    @EzyQuery("{\$query:{name:{\$lt:?0}}, \$orderby:{name:1}}")
    fun findByNameLt(name: String, next: Next): List<Book>

    @EzyQuery("{\$query:{name:{\$gt:?0}}, \$orderby:{name:1}}")
    fun findByNameGt(name: String, next: Next): List<Book>

    @EzyQuery("[{ \$group: { _id : 'sum', sum : { \$sum: {\$toDecimal: \'\$price'}} } }]")
    fun sumPrice(): SumBookPriceResult
}