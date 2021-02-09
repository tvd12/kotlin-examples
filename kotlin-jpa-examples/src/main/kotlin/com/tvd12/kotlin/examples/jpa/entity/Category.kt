package com.tvd12.kotlin.examples.jpa.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Category(
    @Id
    var id: Long,
    var name: String
)