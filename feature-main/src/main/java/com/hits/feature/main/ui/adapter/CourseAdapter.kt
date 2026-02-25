package com.hits.feature.main.ui.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hits.feature.main.databinding.CourseItemBinding

class CourseAdapter(
    private val onClick: (CourseUiModel) -> Unit,
    private val onLikeClick: (CourseUiModel) -> Unit
) : ListAdapter<CourseUiModel, CourseViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = CourseItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CourseViewHolder(binding, onClick, onLikeClick)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object Diff : DiffUtil.ItemCallback<CourseUiModel>() {
        override fun areItemsTheSame(old: CourseUiModel, new: CourseUiModel) =
            old.id == new.id

        override fun areContentsTheSame(old: CourseUiModel, new: CourseUiModel) =
            old == new
    }
}