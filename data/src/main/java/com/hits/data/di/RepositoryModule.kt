package com.hits.data.di

import com.hits.data.repositoryImpl.CourseRepositoryImpl
import com.hits.domain.repository.CourseRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CourseRepository> { CourseRepositoryImpl(get()) }
}