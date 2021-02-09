package com.tvd12.kotlin.examples.jpa.controller

import com.tvd12.ezydata.database.repository.EzyMaxIdRepository
import com.tvd12.ezyhttp.core.exception.HttpBadRequestException
import com.tvd12.ezyhttp.server.core.annotation.Controller
import com.tvd12.ezyhttp.server.core.annotation.DoPost
import com.tvd12.ezyhttp.server.core.annotation.RequestBody
import com.tvd12.kotlin.examples.jpa.entity.Category
import com.tvd12.kotlin.examples.jpa.repository.CategoryRepository
import com.tvd12.kotlin.examples.jpa.request.AddCategoryRequest
import java.lang.IllegalArgumentException

@Controller("/api/v1/category")
class CategoryController(
    private val categoryRepository: CategoryRepository,
    private val maxIdRepository: EzyMaxIdRepository
) {

    @DoPost("/add")
    fun addCategory(@RequestBody request: AddCategoryRequest): Category {
        val existedCategory = categoryRepository.findByName(request.categoryName)
        if (existedCategory != null) {
            throw HttpBadRequestException("category named: ${request.categoryName} existed")
        }
        val category = Category(
            id = maxIdRepository.incrementAndGet("category"),
            name = request.categoryName
        )
        categoryRepository.save(category)
        return category
    }

}