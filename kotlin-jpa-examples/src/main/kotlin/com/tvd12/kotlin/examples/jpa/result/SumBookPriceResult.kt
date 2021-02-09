package com.tvd12.kotlin.examples.jpa.result

import com.tvd12.ezydata.database.annotation.EzyQueryResult
import java.math.BigDecimal

@EzyQueryResult
data class SumBookPriceResult(
    val sum: BigDecimal
)