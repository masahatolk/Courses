package com.hits.domain.repository

import com.hits.domain.model.CourseDomainModel
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    suspend fun addFavorite(course: CourseDomainModel)
    suspend fun removeFavorite(courseId: Int)
    fun getFavoriteCourses(): Flow<List<CourseDomainModel>>
    fun getFavoriteIds(): Flow<List<Int>>
}