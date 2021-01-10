package com.tvd12.kotlin.examples.rpc.test

import com.tvd12.kotlin.examples.rpc.request.GreetRequest
import com.tvd12.kotlin.examples.rpc.response.GreetResponse
import com.tvd12.quick.rpc.client.QuickRpcClient
import com.tvd12.quick.rpc.server.QuickRpcServer
import com.tvd12.quick.rpc.server.setting.QuickRpcSettings
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.random.Random

class RpcServerExampleTest {

    private val serverPort = Random.nextInt(10000, 20000)
    private lateinit var client: QuickRpcClient

    @BeforeEach
    fun setup() {
        startServer()
        setupClient()
    }

    private fun startServer() {
        val settings = QuickRpcSettings.builder()
            .port(serverPort)
            .build()
        val server = QuickRpcServer(settings)
            .scan("com.tvd12.kotlin.examples.rpc")
            .addRequestHandler(GreetRequest::class.java) { request, response ->
                response.write(GreetResponse(request.data.who)) }
        server.start()
    }

    private fun setupClient() {
        client = QuickRpcClient.builder()
            .connectTo("127.0.0.1", serverPort)
            .scan("com.tvd12.kotlin.examples.rpc")
            .build()
    }

    @Test
    fun greetTest() {
        // given
        val requestData = GreetRequest("Monkey")

        // when
        val actual = client.call(requestData, GreetResponse::class.java)

        // then
        val expected = GreetResponse("Greet Monkey!")
        assert(actual == expected)
    }

}