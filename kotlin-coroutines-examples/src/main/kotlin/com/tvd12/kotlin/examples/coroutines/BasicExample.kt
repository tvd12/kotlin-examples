package com.tvd12.kotlin.examples.coroutines

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("${Thread.currentThread().name}: Start")
    launch { println("${Thread.currentThread().name}: Hello World") }
    Thread.sleep(1000)
}