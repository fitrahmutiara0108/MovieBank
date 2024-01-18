package com.mandiri.moviebank.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.mandiri.moviebank.adapter.SearchMovieAdapter
import com.mandiri.moviebank.databinding.FragmentSearchBinding
import com.mandiri.moviebank.model.SearchMovieModel
import com.mandiri.moviebank.presentation.home.viewmodel.HomeViewModel
import com.mandiri.moviebank.presentation.movie.MovieDetailActivity
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel() {
        binding.etSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val query = v.text.toString().toLowerCase()

//                 Launch a coroutine
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.searchDataByQuery(query)
                }

                return@setOnEditorActionListener true
            }
            false
        }

        viewModel.searchedMovie.observe(viewLifecycleOwner) {
            setupViewSearchMovie(it)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupViewSearchMovie(data: List<SearchMovieModel>) {
        val searchMovieAdapter = SearchMovieAdapter()
        searchMovieAdapter.setDataMovie(data)

        searchMovieAdapter.itemClickListener { movieId ->
            navigateToDetailMovie(requireActivity(), movieId)
        }

        binding.rvMovieResult.apply {
            adapter = searchMovieAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }
    }

    fun navigateToDetailMovie(activity: Activity, movieId: Int) =
        Intent().apply {
            val intent = Intent(activity, MovieDetailActivity::class.java)
            intent.putExtra("MOVIE_ID", movieId)
            activity.startActivity(intent)
        }
}