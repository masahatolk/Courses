package com.hits.feature.favorites.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.hits.domain.usecase.ObserveFavoritesUseCase
import com.hits.domain.usecase.ToggleFavoriteUseCase
import com.hits.feature.main.ui.adapter.CourseUiModel
import com.hits.feature.main.ui.adapter.toDomain
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val observeFavorites: ObserveFavoritesUseCase,
    private val toggleFavorite: ToggleFavoriteUseCase
) : ViewModel() {

    val favoriteCourses: LiveData<List<CourseUiModel>> = observeFavorites()
        .map { domainList ->
            domainList.map { domain ->
                CourseUiModel(
                    id = domain.id,
                    title = domain.title,
                    description = domain.text,
                    price = domain.price,
                    rating = domain.rate,
                    startDate = domain.startDate,
                    publishDate = domain.publishDate,
                    isLiked = true
                )
            }
        }.asLiveData()

    fun removeFromFavorites(course: CourseUiModel) {
        viewModelScope.launch {
            toggleFavorite(course.toDomain(), isCurrentlyLiked = true)
        }
    }
}