package com.tvd12.kotlin.examples.redis.config

import com.tvd12.ezydata.redis.EzyRedisProxy
import com.tvd12.ezydata.redis.EzyRedisProxyFactory
import com.tvd12.ezyfox.bean.annotation.EzyConfigurationBefore
import com.tvd12.ezyfox.bean.annotation.EzySingleton
import com.tvd12.ezyfox.util.EzyPropertiesAware
import java.util.*

@EzyConfigurationBefore
class RedisConfig: EzyPropertiesAware {
    private lateinit var properties: Properties

    override fun setProperties(properties: Properties) {
        this.properties = properties
    }

    @EzySingleton
    fun openRedisProxy(): EzyRedisProxy =
        EzyRedisProxyFactory.builder()
            .properties(properties)
            .scan("com.tvd12.kotlin.examples.redis.entity")
            .build()
            .newRedisProxy()
}