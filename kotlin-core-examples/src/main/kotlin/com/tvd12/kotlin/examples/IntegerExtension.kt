package com.tvd12.kotlin.examples

import java.math.BigInteger

fun Integer.toBigInteger1(): BigInteger {
    return BigInteger.valueOf(this.toLong())
}

fun main() {
    val value = Integer.valueOf(10)
    println(value.toBigInteger())
}