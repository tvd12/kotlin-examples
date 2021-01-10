package com.tvd12.kotlin.examples.bean

import com.tvd12.ezyfox.bean.EzyBeanContext
import com.tvd12.kotlin.examples.bean.controller.CustomerController

fun main() {
    val beanContext = EzyBeanContext.builder()
        .scan("com.tvd12.kotlin.examples.bean")
        .build()
    val customerController = beanContext.getSingleton(CustomerController::class.java)
    println(customerController.getCustomerById(1))
}