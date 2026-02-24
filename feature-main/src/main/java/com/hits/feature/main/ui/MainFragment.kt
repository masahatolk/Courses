package com.hits.feature.main.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hits.feature.main.R
import com.hits.feature.main.databinding.FragmentMainBinding
import com.hits.feature.main.ui.adapter.CourseAdapter
import com.hits.feature.main.ui.details.CourseDetailsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        val adapter = CourseAdapter(
            onClick = { course ->
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, CourseDetailsFragment.newInstance(course))
                    .addToBackStack(null)
                    .commit()
            },
            onLikeClick = { viewModel.toggleLike(it) }
        )

        binding.coursesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())

        binding.coursesRecyclerView.adapter = adapter

        viewModel.courses.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.sortText.setOnClickListener {
            viewModel.sortByDate()
        }
    }
}