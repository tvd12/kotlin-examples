package com.tvd12.kotlin.examples.rpc.controller

import com.tvd12.ezyfox.exception.BadRequestException
import com.tvd12.kotlin.examples.rpc.request.GreetRequest
import com.tvd12.kotlin.examples.rpc.response.GreetResponse
import com.tvd12.kotlin.examples.rpc.service.GreetService
import com.tvd12.quick.rpc.server.annotation.Rpc
import com.tvd12.quick.rpc.server.annotation.RpcController

@RpcController
class HelloController(
    private val greetService: GreetService
) {
    @Rpc
    fun greet(greetRequest: GreetRequest): GreetResponse {
        if (greetRequest.who.length < 3) {
            throw BadRequestException(1, "name too short!")
        }
        return GreetResponse(
            greetService.greet(greetRequest.who)
        )
    }
}