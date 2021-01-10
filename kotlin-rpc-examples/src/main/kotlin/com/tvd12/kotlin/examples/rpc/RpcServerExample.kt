package com.tvd12.kotlin.examples.rpc

import com.tvd12.quick.rpc.server.QuickRpcServer
import com.tvd12.quick.rpc.server.asm.RpcRequestHandlerImplementer
import com.tvd12.quick.rpc.server.setting.QuickRpcSettings

fun main() {
    RpcRequestHandlerImplementer.setDebug(true)
    val settings = QuickRpcSettings.builder()
            .username("admin")
            .password("admin")
            .build()
    val server = QuickRpcServer(settings)
            .scan("com.tvd12.kotlin.examples.rpc")
    server.start()
}