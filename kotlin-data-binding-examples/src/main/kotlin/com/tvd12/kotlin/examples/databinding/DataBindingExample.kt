package com.tvd12.kotlin.examples.databinding

import com.tvd12.ezyfox.binding.EzyBindingContext
import com.tvd12.ezyfox.binding.impl.EzyObjectReaderBuilder
import com.tvd12.ezyfox.entity.EzyObject

fun main() {
    EzyObjectReaderBuilder.setDebug(true)
    val bindingContext = EzyBindingContext.builder()
        .scan("com.tvd12.kotlin.examples.databinding")
        .build()
    val marshaller = bindingContext.newMarshaller()
    val unmarsahller = bindingContext.newUnmarshaller()

    val map = marshaller.marshal<EzyObject>(GreetRequest("Dzung"))
    println("map: $map")
    val request = unmarsahller.unmarshal(map, GreetRequest::class.java)
    println("request: $request")

}