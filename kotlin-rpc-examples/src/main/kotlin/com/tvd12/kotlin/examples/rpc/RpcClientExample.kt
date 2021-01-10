package com.tvd12.kotlin.examples.rpc

import com.tvd12.kotlin.examples.rpc.request.GreetRequest
import com.tvd12.kotlin.examples.rpc.response.GreetResponse
import com.tvd12.quick.rpc.client.QuickRpcClient
import com.tvd12.quick.rpc.client.entity.RpcRequest

fun main() {
    val rpcClient = QuickRpcClient.builder()
        .scan("com.tvd12.kotlin.examples.rpc")
        .build()
    val greetResponse = rpcClient.call(
        GreetRequest("Monkey"),
        GreetResponse::class.java
    )
    println(greetResponse)
}
