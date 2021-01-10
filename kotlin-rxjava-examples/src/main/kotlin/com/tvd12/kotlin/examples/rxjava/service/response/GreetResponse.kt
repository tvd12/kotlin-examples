package com.tvd12.kotlin.examples.rxjava.service.response

import com.tvd12.quick.rpc.core.annotation.RpcResponse

@RpcResponse
data class GreetResponse(
    val greet: String
)