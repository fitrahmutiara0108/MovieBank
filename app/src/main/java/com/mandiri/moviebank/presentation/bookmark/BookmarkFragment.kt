package com.mandiri.moviebank.presentation.bookmark

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.mandiri.moviebank.adapter.BookmarkAdapter
import com.mandiri.moviebank.data.local.BookmarkEntity
import com.mandiri.moviebank.databinding.FragmentBookmarkBinding
import com.mandiri.moviebank.presentation.bookmark.viewmodel.BookmarkViewModel
import com.mandiri.moviebank.presentation.movie.MovieDetailActivity


class BookmarkFragment : Fragment() {
    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BookmarkViewModel by viewModels {
        BookmarkViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeViewModel() {
        Log.d("tiara", "before load")

        val movieId = arguments?.getInt("MOVIE_ID", -1) ?: -1
        viewModel.loadSavedMovies()
        Log.d("tiara", "after load")

        viewModel.savedMovies.observe(viewLifecycleOwner) { bookmarkEntities ->
            Log.d("tiara", "on observer")

            setupViewBookmark(bookmarkEntities)
            Log.d("MovieDetailActivity", "Saved movies changed: $bookmarkEntities")
        }
    }

    override fun onResume() {
        super.onResume()

        observeViewModel()
    }

    private fun setupViewBookmark(data: List<BookmarkEntity>) {
        Log.d("BookmarkFragment", "Setting up bookmark view with data: $data")
        val bookmarkAdapter = BookmarkAdapter()
        bookmarkAdapter.setDataMovie(data)

        bookmarkAdapter.itemClickListener { movieId ->
            navigateToDetailMovie(requireActivity(), movieId)
        }

        binding.bookmark.rvBookmark.apply {
            Log.d("BookmarkFragment", "Setting adapter and layoutManager")
            adapter = bookmarkAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }
    }

    fun navigateToDetailMovie(activity: Activity, movieId: Int) {
        val intent = Intent(activity, MovieDetailActivity::class.java)
        intent.putExtra("MOVIE_ID", movieId)
        activity.startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
