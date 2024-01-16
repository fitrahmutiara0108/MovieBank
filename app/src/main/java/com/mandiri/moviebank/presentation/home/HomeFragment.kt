package com.mandiri.moviebank.presentation.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandiri.moviebank.adapter.PopularMovieAdapter
import com.mandiri.moviebank.adapter.RecentMovieAdapter
import com.mandiri.moviebank.databinding.FragmentHomeBinding
import com.mandiri.moviebank.model.PopularMovieModel
import com.mandiri.moviebank.model.RecentMovieModel
import com.mandiri.moviebank.presentation.MovieDetailActivity
import com.mandiri.moviebank.presentation.home.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupView(){
        viewModel.setData()

        observeViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun observeViewModel() {
        viewModel.homePopularMovie.observe(viewLifecycleOwner) {
//            dataPopularMovie = it
            setupViewPopularMovie(it)

        }
        viewModel.homeRecentMovie.observe(viewLifecycleOwner) {
            setupViewRecentMovie(it)
        }
    }

    private fun setupViewPopularMovie(data: MutableList<PopularMovieModel>) {
        val popularMovieAdapter = PopularMovieAdapter()
        popularMovieAdapter.setDataPopularMovie(data)

        popularMovieAdapter.itemClickListener {
            movieId -> navigateToDetailMovie(requireActivity(), movieId) }

        binding.popularMovies.rvPopularMovie.apply {
            adapter = popularMovieAdapter
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        }
    }

    private fun setupViewRecentMovie(data: MutableList<RecentMovieModel>) {
        val recentMovieAdapter = RecentMovieAdapter()
        recentMovieAdapter.setDataRecentMovie(data)

        recentMovieAdapter.itemClickListener {
            movieId -> navigateToDetailMovie(requireActivity(), movieId)
        }

        binding.recentMovies.rvRecentMovie.apply {
            adapter = recentMovieAdapter
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        }
    }

    fun navigateToDetailMovie(activity: Activity, movieId: Int) =
        Intent().apply {
            val intent = Intent(activity, MovieDetailActivity::class.java)
            intent.putExtra("MOVIE_ID", movieId)
            activity.startActivity(intent)
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
