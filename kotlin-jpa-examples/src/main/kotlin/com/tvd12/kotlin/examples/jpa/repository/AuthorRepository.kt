package com.tvd12.kotlin.examples.jpa.repository

import com.tvd12.ezydata.database.EzyDatabaseRepository
import com.tvd12.ezyfox.annotation.EzyAutoImpl
import com.tvd12.kotlin.examples.jpa.entity.Author

@EzyAutoImpl
interface AuthorRepository: EzyDatabaseRepository<Long, Author>