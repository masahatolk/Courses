package com.hits.feature.main.ui.adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hits.core.ui.R
import com.hits.feature.main.databinding.CourseItemBinding

class CourseViewHolder(
    private val binding: CourseItemBinding,
    private val onClick: (CourseUiModel) -> Unit,
    private val onLikeClick: (CourseUiModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CourseUiModel) = with(binding) {

        title.text = item.title
        description.text = item.description
        price.text = item.price
        rating.text = item.rating
        date.text = item.startDate

        favorite.setColorFilter(
            ContextCompat.getColor(
                root.context,
                if (item.isLiked) R.color.green else android.R.color.white
            )
        )

        root.setOnClickListener { onClick(item) }
        favorite.setOnClickListener { onLikeClick(item) }
    }
}