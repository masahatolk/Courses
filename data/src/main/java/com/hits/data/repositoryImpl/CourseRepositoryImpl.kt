package com.hits.data.repositoryImpl

import com.hits.core.network.ApiService
import com.hits.domain.model.Course
import com.hits.domain.repository.CourseRepository

class CourseRepositoryImpl(
    private val api: ApiService
) : CourseRepository {

    override suspend fun getCourses(): List<Course> {
        return api.getCourses().courses.map { it.toDomain() }
    }
}