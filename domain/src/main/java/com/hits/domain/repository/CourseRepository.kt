package com.hits.domain.repository

import com.hits.domain.model.CourseDomainModel

interface CourseRepository {
    suspend fun getCourses(): List<CourseDomainModel>
}