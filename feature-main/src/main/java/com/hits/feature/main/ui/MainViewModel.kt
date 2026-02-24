package com.hits.feature.main.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hits.domain.usecase.GetCoursesUseCase
import com.hits.feature.main.ui.adapter.CourseUiModel
import kotlinx.coroutines.launch

class MainViewModel(
    private val getCourses: GetCoursesUseCase
) : ViewModel() {

    private val _courses = MutableLiveData<List<CourseUiModel>>()
    val courses: LiveData<List<CourseUiModel>> = _courses

    private val likedIds = mutableSetOf<String>()

    init {
        loadCourses()
    }

    private fun loadCourses() = viewModelScope.launch {
        val result = getCourses()

        val mapped = result.map {
            CourseUiModel(
                id = it.id,
                title = it.title,
                description = it.text,
                price = "${it.price} ₽",
                rating = it.rate.toString(),
                startDate = it.startDate,
                publishDate = it.publishDate,
                isLiked = it.hasLike
            )
        }

        _courses.value = sortDesc(mapped)
    }

    fun toggleLike(course: CourseUiModel) {
        likedIds.toggle(course.id)

        _courses.value = _courses.value?.map {
            if (it.id == course.id) it.copy(isLiked = !it.isLiked) else it
        }
    }

    fun sortByDate() {
        _courses.value = _courses.value?.let { sortDesc(it) }
    }

    private fun sortDesc(list: List<CourseUiModel>) =
        list.sortedByDescending { it.publishDate }

    private fun MutableSet<String>.toggle(id: String) {
        if (contains(id)) remove(id) else add(id)
    }
}