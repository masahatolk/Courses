package com.hits.feature.main.ui.adapter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CourseUiModel(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val rating: String,
    val startDate: String,
    val publishDate: String,
    val isLiked: Boolean
) : Parcelable