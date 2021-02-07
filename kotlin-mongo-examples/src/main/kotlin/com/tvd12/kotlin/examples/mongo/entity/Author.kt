package com.tvd12.kotlin.examples.mongo.entity

import com.tvd12.ezydata.database.annotation.EzyCollection
import com.tvd12.ezyfox.annotation.EzyId

@EzyCollection
data class Author(
    @EzyId
    var id: Long,
    var name: String
)