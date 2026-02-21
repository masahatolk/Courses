package com.hits.courses

import android.app.Application
import com.hits.feature.main.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                mainModule
            )
        }
    }
}