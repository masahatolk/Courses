package com.hits.courses

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.hits.data.coreDatabaseModule
import com.hits.domain.di.domainModule
import com.hits.feature.favorites.ui.FavoritesViewModel
import com.hits.feature.main.di.mainModule
import com.hits.feature.main.ui.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class App : Application() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                coreDatabaseModule,
                domainModule,
                mainModule,
                module {
                    viewModel { MainViewModel(get(), get(), get()) }
                    viewModel { FavoritesViewModel(get(), get()) }
                }
            )
        }
    }
}