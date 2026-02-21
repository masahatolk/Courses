package com.hits.domain.repository

import com.hits.domain.model.Course

interface CourseRepository {
    suspend fun getCourses(): List<Course>
}