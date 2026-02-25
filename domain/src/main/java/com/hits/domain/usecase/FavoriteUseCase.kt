package com.hits.domain.usecase

import com.hits.domain.model.CourseDomainModel
import com.hits.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow

class ToggleFavoriteUseCase(private val repository: FavoritesRepository) {
    suspend operator fun invoke(course: CourseDomainModel, isCurrentlyLiked: Boolean) {
        if (isCurrentlyLiked) {
            repository.removeFavorite(course.id)
        } else {
            repository.addFavorite(course)
        }
    }
}

class ObserveFavoriteIdsUseCase(private val repository: FavoritesRepository) {
    operator fun invoke(): Flow<List<Int>> = repository.getFavoriteIds()
}

class ObserveFavoritesUseCase(private val repository: FavoritesRepository) {
    operator fun invoke(): Flow<List<CourseDomainModel>> = repository.getFavoriteCourses()
}