package com.mandiri.moviebank.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mandiri.moviebank.adapter.SearchMovieAdapter
import com.mandiri.moviebank.databinding.FragmentVideoScreenBinding
import com.mandiri.moviebank.model.SearchMovieModel
import com.mandiri.moviebank.presentation.home.viewmodel.HomeViewModel
import com.mandiri.moviebank.presentation.movie.MovieDetailActivity

class PlayVideoFragment : Fragment() {
    private var _binding: FragmentVideoScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVideoScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel() {
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

//        binding.rvMovieResult.apply {
//            adapter = searchMovieAdapter
//            layoutManager = GridLayoutManager(activity, 2)
//        }
    }

    fun navigateToDetailMovie(activity: Activity, movieId: Int) =
        Intent().apply {
            val intent = Intent(activity, MovieDetailActivity::class.java)
            intent.putExtra("MOVIE_ID", movieId)
            activity.startActivity(intent)
        }
}