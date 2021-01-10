package com.tvd12.kotlin.examples.databinding

import com.tvd12.ezyfox.binding.annotation.EzyObjectBinding

@EzyObjectBinding
data class GreetRequest(
    val who: String
)