package com.hits.data

import com.hits.domain.model.CourseDomainModel

fun FavoriteCourseEntity.toDomain(): CourseDomainModel {
    return CourseDomainModel(
        id = id,
        title = title,
        text = description,
        price = price,
        rate = rating,
        startDate = startDate,
        publishDate = publishDate,
        hasLike = true
    )
}

fun CourseDomainModel.toEntity(): FavoriteCourseEntity {
    return FavoriteCourseEntity(
        id = id,
        title = title,
        description = text,
        price = price,
        rating = rate,
        startDate = startDate,
        publishDate = publishDate
    )
}