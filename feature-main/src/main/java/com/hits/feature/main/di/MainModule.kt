package com.hits.feature.main.di

import com.hits.domain.repository.CourseRepository
import com.hits.domain.usecase.GetCoursesUseCase
import com.hits.feature.main.data.FakeCourseRepository
import com.hits.feature.main.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    single<CourseRepository> { FakeCourseRepository() }

    factory { GetCoursesUseCase(get()) }

    viewModel { MainViewModel(get()) }
}