package com.tvd12.kotlin.examples.mongo

import com.tvd12.ezyhttp.core.boot.EzyHttpApplicationBootstrap

class KotlinMongoApplication

fun main() {
    EzyHttpApplicationBootstrap.start(KotlinMongoApplication::class.java)
}