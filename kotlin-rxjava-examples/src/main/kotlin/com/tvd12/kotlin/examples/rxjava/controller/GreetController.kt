package com.tvd12.kotlin.examples.rxjava.controller

import com.tvd12.ezyhttp.core.response.ResponseEntity
import com.tvd12.ezyhttp.server.core.annotation.Controller
import com.tvd12.ezyhttp.server.core.annotation.DoGet
import com.tvd12.ezyhttp.server.core.annotation.PathVariable
import com.tvd12.kotlin.examples.rxjava.service.GreetService

@Controller("/api/v1")
class GreetController(
    private val greetService: GreetService
) {
    @DoGet("/greet/{who}")
    fun greet(
        @PathVariable("who") who: String
    ): ResponseEntity =
        greetService.greet(who)
            .map { ResponseEntity.ok(it) }
            .blockingGet()

}