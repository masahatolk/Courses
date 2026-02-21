package com.hits.feature.main.di

import com.hits.feature.main.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    viewModel {
        MainViewModel(get())
    }

}