package com.hits.data.repositoryImpl

import com.hits.data.FavoritesDao
import com.hits.data.toDomain
import com.hits.data.toEntity
import com.hits.domain.model.CourseDomainModel
import com.hits.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritesRepositoryImpl(
    private val favoritesDao: FavoritesDao
) : FavoritesRepository {

    override suspend fun addFavorite(course: CourseDomainModel) {
        favoritesDao.addFavorite(course.toEntity())
    }

    override suspend fun removeFavorite(courseId: Int) {
        favoritesDao.removeFavorite(courseId)
    }

    override fun getFavoriteCourses(): Flow<List<CourseDomainModel>> {
        return favoritesDao.getFavoriteCourses().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getFavoriteIds(): Flow<List<Int>> {
        return favoritesDao.getFavoriteIds()
    }
}