package com.hits.core.network

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(ApiService::class.java) }
}