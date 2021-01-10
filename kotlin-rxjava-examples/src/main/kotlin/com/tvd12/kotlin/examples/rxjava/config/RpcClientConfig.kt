package com.tvd12.kotlin.examples.rxjava.config

import com.tvd12.ezyfox.bean.annotation.EzyConfiguration
import com.tvd12.ezyfox.bean.annotation.EzySingleton
import com.tvd12.quick.rpc.client.QuickRpcClient

@EzyConfiguration
class RpcClientConfig {
    @EzySingleton
    fun rpcClient(): QuickRpcClient {
        return QuickRpcClient.builder()
            .scan("com.tvd12.kotlin.examples.rxjava")
            .build()
    }
}