package com.tvd12.kotlin.examples.properties_file

import com.tvd12.properties.file.mapping.PropertiesMapper

data class ApplicationConfig(
    private val server: Server,
    private val cors: Cors,
    private val hello: String,
    private val foo: String,
    private val dataSources: Map<String, DataSource>
)

data class Server(
    private val host: String,
    private val port: String,
    private val admin: Admin
)

data class Admin(
    private val username: String,
    private val password: String
)

data class Cors(
    private val enable: Boolean,
    private val allowOrigin: String
)

data class DataSource(
    private val url: String,
    private val username: String,
    private val password: String
)

fun main() {
    val applicationConfig = PropertiesMapper()
        .file("application.yaml")
        .map(ApplicationConfig::class.java)
    println(applicationConfig)
}