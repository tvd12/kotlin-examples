package com.tvd12.kotlin.examples.redis.controller

import com.tvd12.ezydata.redis.EzyRedisProxy
import com.tvd12.ezyhttp.core.exception.HttpBadRequestException
import com.tvd12.ezyhttp.server.core.annotation.Controller
import com.tvd12.ezyhttp.server.core.annotation.DoPost
import com.tvd12.ezyhttp.server.core.annotation.RequestBody
import com.tvd12.kotlin.examples.redis.entity.Category
import com.tvd12.kotlin.examples.redis.request.AddCategoryRequest

@Controller("/api/v1/category")
class CategoryController(
    redisProxy: EzyRedisProxy
) {
    private val idGentor = redisProxy.getAtomicLong("category")
    private val categoryMap = redisProxy.getMap<Long, Category>("category")
    private val categoryIdByNameMap =
        redisProxy.getMap("categoryIdByName", String::class.java, Long::class.java)

    @DoPost("/add")
    fun addCategory(@RequestBody request: AddCategoryRequest): Category {
        val existedCategoryId = categoryIdByNameMap[request.categoryName]
        if (existedCategoryId != null) {
            throw HttpBadRequestException("category named: ${request.categoryName} existed")
        }
        val category = Category(
            id = idGentor.incrementAndGet(),
            name = request.categoryName
        )
        categoryMap[category.id] = category
        categoryIdByNameMap[category.name] = category.id
        return category
    }

}