package com.tvd12.kotlin.examples.bean.controller

import com.tvd12.ezyfox.bean.annotation.EzySingleton
import com.tvd12.kotlin.examples.bean.service.CustomerService

@EzySingleton
class CustomerController(
    private val customerService: CustomerService
) {
    fun getCustomerById(customerId: Long): String =
        customerService.getCustomerById(customerId)
            .map { it.toString() }
            .orElse("NOT FOUND")
}