package com.tvd12.kotlin.examples.rpc.request

import com.tvd12.quick.rpc.core.annotation.RpcRequest

@RpcRequest
data class GreetRequest(
    val who: String
)