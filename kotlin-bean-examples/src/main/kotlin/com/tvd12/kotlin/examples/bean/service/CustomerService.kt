package com.tvd12.kotlin.examples.bean.service

import com.tvd12.ezyfox.bean.annotation.EzySingleton
import com.tvd12.kotlin.examples.bean.entity.Customer
import com.tvd12.kotlin.examples.bean.repository.CustomerRepository
import java.util.*

@EzySingleton
class CustomerService(
    private val customerRepository: CustomerRepository
) {
    fun getCustomerById(customerId: Long): Optional<Customer> =
        customerRepository.findById(customerId)
}