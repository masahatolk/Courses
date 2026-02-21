package com.hits.domain.usecase

import com.hits.domain.repository.CourseRepository

class GetCoursesUseCase(
    private val repo: CourseRepository
) {
    suspend operator fun invoke() = repo.getCourses()
}