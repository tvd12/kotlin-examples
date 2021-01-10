package com.tvd12.kotlin.examples.rxjava.service

import com.tvd12.ezyfox.bean.annotation.EzySingleton
import com.tvd12.kotlin.examples.rxjava.service.request.GreetRequest
import com.tvd12.kotlin.examples.rxjava.service.response.GreetResponse
import com.tvd12.quick.rpc.client.QuickRpcClient
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

@EzySingleton
class GreetService(
    private val rpcClient: QuickRpcClient
) {
    fun greet(who: String): Single<String> =
        Single.fromCallable {
            GreetRequest(who)
        }
            .flatMap {
                Single.fromFuture(rpcClient.submit(it, GreetResponse::class.java))
            }
            .map { it.greet }
            .subscribeOn(Schedulers.io())
}