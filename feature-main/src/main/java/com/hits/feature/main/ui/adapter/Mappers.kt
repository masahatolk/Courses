package com.hits.feature.main.ui.adapter

import com.hits.domain.model.CourseDomainModel

fun CourseUiModel.toDomain(): CourseDomainModel {
    return CourseDomainModel(
        id = id,
        title = title,
        text = description,
        price = price,
        rate = rating,
        startDate = startDate,
        publishDate = publishDate,
        hasLike = isLiked
    )
}