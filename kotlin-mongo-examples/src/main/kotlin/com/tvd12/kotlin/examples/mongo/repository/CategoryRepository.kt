package com.tvd12.kotlin.examples.mongo.repository

import com.tvd12.ezydata.mongodb.EzyMongoRepository
import com.tvd12.ezyfox.annotation.EzyAutoImpl
import com.tvd12.kotlin.examples.mongo.entity.Category

@EzyAutoImpl
interface CategoryRepository: EzyMongoRepository<Long, Category> {

    fun findByName(name: String): Category?
}