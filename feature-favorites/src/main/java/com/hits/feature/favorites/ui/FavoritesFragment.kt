package com.hits.feature.favorites.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hits.feature.favorites.databinding.FragmentFavoritesBinding
import com.hits.feature.main.ui.adapter.CourseAdapter
import com.hits.feature.main.ui.details.CourseDetailsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment(com.hits.feature.favorites.R.layout.fragment_favorites) {

    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoritesBinding.bind(view)

        val adapter = CourseAdapter(
            onClick = { course ->
                parentFragmentManager.beginTransaction()
                    .replace(
                        com.hits.feature.main.R.id.fragment_container,
                        CourseDetailsFragment.newInstance(course)
                    )
                    .addToBackStack(null)
                    .commit()
            },
            onLikeClick = { course ->
                viewModel.removeFromFavorites(course)
            }
        )

        binding.favoritesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.favoritesRecyclerView.adapter = adapter

        viewModel.favoriteCourses.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }
}