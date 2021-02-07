package com.tvd12.kotlin.examples.mongo.config

import com.mongodb.MongoClient
import com.tvd12.ezydata.mongodb.EzyMongoDatabaseContext
import com.tvd12.ezydata.mongodb.EzyMongoDatabaseContextBuilder
import com.tvd12.ezydata.mongodb.loader.EzySimpleMongoClientLoader
import com.tvd12.ezyfox.bean.EzyBeanConfig
import com.tvd12.ezyfox.bean.EzySingletonFactory
import com.tvd12.ezyfox.bean.annotation.EzyAutoBind
import com.tvd12.ezyfox.bean.annotation.EzyConfigurationBefore
import com.tvd12.ezyfox.bean.annotation.EzyPropertiesBean
import com.tvd12.ezyfox.util.EzyPropertiesAware
import java.util.*

@EzyConfigurationBefore
@EzyPropertiesBean(MongoProperties::class, prefix = "database.mongo")
class MongoConfig: EzyBeanConfig, EzyPropertiesAware {

    @EzyAutoBind
    private lateinit var mongoProperties: MongoProperties

    private lateinit var properties: Properties

    override fun setProperties(properties: Properties) {
        this.properties = properties
    }

    @EzyAutoBind
    private lateinit var singletonFactory: EzySingletonFactory

    override fun config() {
        val databaseContext = databaseContext()
        databaseContext.repositoriesByName.forEach {
            singletonFactory.addSingleton(it.key, it.value)
        }
    }

    private fun databaseContext(): EzyMongoDatabaseContext =
        EzyMongoDatabaseContextBuilder()
            .databaseName(mongoProperties.database)
            .mongoClient(mongoClient())
            .properties(properties)
            .scan("com.tvd12.kotlin.examples.mongo.entity")
            .scan("com.tvd12.kotlin.examples.mongo.repository")
            .scan("com.tvd12.kotlin.examples.mongo.result")
            .build()

    private fun mongoClient(): MongoClient =
        EzySimpleMongoClientLoader()
            .uri(mongoProperties.uri)
            .load()

}