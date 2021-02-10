package com.tvd12.kotlin.examples.jpa.entity

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,
    var categoryId: Long,
    var authorId: Long,
    var name: String,
    var price: BigDecimal,
    var releaseDate: LocalDate,
    var releaseTime: LocalDateTime
)