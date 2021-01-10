package com.tvd12.kotlin.examples.bean.repository

import com.tvd12.ezyfox.bean.annotation.EzySingleton
import com.tvd12.kotlin.examples.bean.entity.Customer
import java.util.*

@EzySingleton
class CustomerRepository {
    private val customers = mapOf(
        1L to Customer(
            id = 1L,
            username = "customer1",
            firstName = "Monkey",
            lastName = "Young"
        )
    )
    fun findById(id: Long): Optional<Customer> =
        Optional.ofNullable(customers[id])
}