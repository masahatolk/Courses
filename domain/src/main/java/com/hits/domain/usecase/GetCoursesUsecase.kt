package com.hits.domain.usecase

import com.hits.domain.repository.CourseRepository

class GetCoursesUseCase(
    private val repository: CourseRepository
) {
    suspend operator fun invoke() = repository.getCourses()
}