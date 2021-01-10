package com.tvd12.kotlin.examples.rpc.service

import com.tvd12.ezyfox.bean.annotation.EzySingleton

@EzySingleton
class GreetService {
    fun greet(who: String): String =
        "Greet $who!"
}