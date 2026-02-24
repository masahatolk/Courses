package com.hits.feature.main.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.hits.feature.main.R
import com.hits.feature.main.databinding.FragmentCourseDetailsBinding
import com.hits.feature.main.ui.adapter.CourseUiModel

class CourseDetailsFragment : Fragment(R.layout.fragment_course_details) {

    private lateinit var binding: FragmentCourseDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCourseDetailsBinding.bind(view)

        val course = requireArguments().getParcelable<CourseUiModel>("course")!!

//        binding.title.text = course.title
//        binding.description.text = course.description
//        binding.price.text = course.price
//        binding.rating.text = course.rating
    }

    companion object {
        fun newInstance(course: CourseUiModel) =
            CourseDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("course", course)
                }
            }
    }
}