package com.hits.feature.main.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hits.domain.usecase.GetCoursesUseCase
import com.hits.domain.usecase.ObserveFavoriteIdsUseCase
import com.hits.domain.usecase.ToggleFavoriteUseCase
import com.hits.feature.main.ui.adapter.CourseUiModel
import com.hits.feature.main.ui.adapter.toDomain
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class MainViewModel(
    private val getCourses: GetCoursesUseCase,
    private val observeFavoriteIds: ObserveFavoriteIdsUseCase,
    private val toggleFavorite: ToggleFavoriteUseCase
) : ViewModel() {

    private val _courses = MutableLiveData<List<CourseUiModel>>()
    val courses: LiveData<List<CourseUiModel>> = _courses

    init {
        loadCourses()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadCourses() = viewModelScope.launch {
        val allCourses = getCourses()

        observeFavoriteIds().collect { likedIds ->
            val mapped = allCourses.map { domainModel ->
                CourseUiModel(
                    id = domainModel.id,
                    title = domainModel.title,
                    description = domainModel.text,
                    price = domainModel.price,
                    rating = domainModel.rate,
                    startDate = domainModel.startDate,
                    publishDate = domainModel.publishDate,
                    isLiked = likedIds.contains(domainModel.id)
                )
            }
            _courses.value = sortDesc(mapped)
        }
    }

    fun toggleLike(course: CourseUiModel) {
        viewModelScope.launch {
            toggleFavorite(course.toDomain(), course.isLiked)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sortDesc(list: List<CourseUiModel>): List<CourseUiModel> {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return list.sortedByDescending { LocalDate.parse(it.publishDate, formatter) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sortByDate() {
        _courses.value = _courses.value?.let { sortDesc(it) }
    }
}