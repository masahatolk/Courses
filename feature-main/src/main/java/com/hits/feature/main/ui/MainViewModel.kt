package com.hits.feature.main.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hits.domain.usecase.GetCoursesUseCase

class MainViewModel(
    private val getCourses: GetCoursesUseCase
) : ViewModel() {

    val courses = liveData {
        emit(getCourses())
    }
}