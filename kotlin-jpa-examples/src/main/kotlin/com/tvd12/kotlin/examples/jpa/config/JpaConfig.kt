package com.tvd12.kotlin.examples.jpa.config

import com.tvd12.ezydata.database.EzyDatabaseContext
import com.tvd12.ezydata.jpa.EzyJpaDatabaseContextBuilder
import com.tvd12.ezydata.jpa.loader.EzyJpaDataSourceLoader
import com.tvd12.ezydata.jpa.loader.EzyJpaEntityManagerFactoryLoader
import com.tvd12.ezyfox.bean.EzyBeanConfig
import com.tvd12.ezyfox.bean.EzySingletonFactory
import com.tvd12.ezyfox.bean.annotation.EzyAutoBind
import com.tvd12.ezyfox.bean.annotation.EzyConfigurationBefore
import com.tvd12.ezyfox.util.EzyPropertiesAware
import java.util.*
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource


@EzyConfigurationBefore
class JpaConfig: EzyBeanConfig, EzyPropertiesAware {

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

    private fun databaseContext(): EzyDatabaseContext =
        EzyJpaDatabaseContextBuilder()
            .properties(properties)
            .entityManagerFactory(entityManagerFactory())
            .scan("com.tvd12.kotlin.examples.jpa.entity")
            .scan("com.tvd12.kotlin.examples.jpa.repository")
            .scan("com.tvd12.kotlin.examples.jpa.result")
            .build()

    private fun entityManagerFactory(): EntityManagerFactory? {
        return EzyJpaEntityManagerFactoryLoader()
            .entityPackage("com.tvd12.kotlin.examples.jpa.entity")
            .dataSource(dataSource())
            .properties(properties)
            .load("Test")
    }

    private fun dataSource(): DataSource? {
        return EzyJpaDataSourceLoader()
            .properties(properties, "datasource")
            .load()
    }

}