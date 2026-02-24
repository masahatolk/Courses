package com.hits.feature.main.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hits.domain.usecase.GetCoursesUseCase
import com.hits.feature.main.ui.adapter.CourseUiModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class MainViewModel(
    private val getCourses: GetCoursesUseCase
) : ViewModel() {

    private val _courses = MutableLiveData<List<CourseUiModel>>()
    val courses: LiveData<List<CourseUiModel>> = _courses

    private val likedIds = mutableSetOf<Int>()

    init {
        loadCourses()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadCourses() = viewModelScope.launch {
        val result = getCourses()

        val mapped = result.map {
            CourseUiModel(
                id = it.id,
                title = it.title,
                description = it.text,
                price = "${it.price} ₽",
                rating = it.rate,
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun sortByDate() {
        _courses.value = _courses.value?.let { sortDesc(it) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sortDesc(list: List<CourseUiModel>): List<CourseUiModel> {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return list.sortedByDescending { LocalDate.parse(it.publishDate, formatter) }
    }

    private fun MutableSet<Int>.toggle(id: Int) {
        if (contains(id)) remove(id) else add(id)
    }
}