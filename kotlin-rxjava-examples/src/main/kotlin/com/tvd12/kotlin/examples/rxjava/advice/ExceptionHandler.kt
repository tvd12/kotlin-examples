package com.tvd12.kotlin.examples.rxjava.advice

import com.tvd12.ezyhttp.core.constant.StatusCodes
import com.tvd12.ezyhttp.core.response.ResponseEntity
import com.tvd12.ezyhttp.server.core.annotation.ExceptionHandler
import com.tvd12.ezyhttp.server.core.annotation.TryCatch
import com.tvd12.quick.rpc.client.exception.RpcErrorException
import java.lang.RuntimeException
import java.util.concurrent.ExecutionException

@ExceptionHandler
class ExceptionHandler {

    @TryCatch(RuntimeException::class)
    fun handleRuntimeException(e: RuntimeException): ResponseEntity {
        if (e.cause is ExecutionException) {
            val executionException = e.cause as ExecutionException
            return handleExecutionException(executionException)
        }
        return handleException(e)
    }

    @TryCatch(ExecutionException::class)
    fun handleExecutionException(e: ExecutionException): ResponseEntity {
        if (e.cause is RpcErrorException) {
            val rpcException = e.cause as RpcErrorException
            return ResponseEntity.ok(rpcException.error.rawData)
        }
        return handleException(e)
    }

    @TryCatch(Exception::class)
    fun handleException(e: Exception): ResponseEntity =
        ResponseEntity.of(
            StatusCodes.INTERNAL_SERVER_ERROR,
            e.message
        )
            .build()
}