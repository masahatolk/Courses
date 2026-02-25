package com.hits.data

import androidx.room.Room
import com.hits.data.repositoryImpl.FavoritesRepositoryImpl
import com.hits.domain.repository.FavoritesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import kotlin.jvm.java

val coreDatabaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "courses_database"
        ).build()
    }

    single { get<AppDatabase>().favoritesDao() }

    single<FavoritesRepository> { FavoritesRepositoryImpl(get()) }
}