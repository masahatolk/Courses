package com.hits.domain.di

import com.hits.domain.usecase.GetCoursesUseCase
import com.hits.domain.usecase.ObserveFavoriteIdsUseCase
import com.hits.domain.usecase.ObserveFavoritesUseCase
import com.hits.domain.usecase.ToggleFavoriteUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetCoursesUseCase(get()) }
    factory { ToggleFavoriteUseCase(get()) }
    factory { ObserveFavoriteIdsUseCase(get()) }
    factory { ObserveFavoritesUseCase(get()) }
}