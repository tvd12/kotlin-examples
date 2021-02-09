package com.tvd12.kotlin.examples.jpa.entity

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Book(
    @Id
    var id: Long,
    var categoryId: Long,
    var authorId: Long,
    var name: String,
    var price: BigDecimal,
    var releaseDate: LocalDate,
    var releaseTime: LocalDateTime
)