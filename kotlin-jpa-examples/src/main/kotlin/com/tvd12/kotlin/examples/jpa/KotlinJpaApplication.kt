package com.tvd12.kotlin.examples.jpa

import com.tvd12.ezydata.database.bean.EzyAbstractRepositoryImplementer
import com.tvd12.ezyhttp.core.boot.EzyHttpApplicationBootstrap

class KotlinJpaApplication

fun main() {
    EzyAbstractRepositoryImplementer.setDebug(true);
    EzyHttpApplicationBootstrap.start(KotlinJpaApplication::class.java)
}