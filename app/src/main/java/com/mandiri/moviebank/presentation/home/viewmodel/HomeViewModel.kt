package com.mandiri.moviebank.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mandiri.moviebank.R
import com.mandiri.moviebank.model.PopularMovieModel
import com.mandiri.moviebank.model.RecentMovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {
    private val _homePopularMovie = MutableLiveData<MutableList<PopularMovieModel>>()
    private val _homeRecentMovie = MutableLiveData<MutableList<RecentMovieModel>>()

    val homePopularMovie: LiveData<MutableList<PopularMovieModel>> = _homePopularMovie
    val homeRecentMovie: LiveData<MutableList<RecentMovieModel>> = _homeRecentMovie

//    init {
//        // Make API calls here and update LiveData
//        viewModelScope.launch {
//            // Example:
//            try {
//                val popularMovies = movieRepository.getPopularMovies()
//                _homePopularMovie.postValue(popularMovies)
//
//                val recentMovies = movieRepository.getRecentMovies()
//                _homeRecentMovie.postValue(recentMovies)
//            } catch (e: Exception) {
//                // Handle error
//            }
//        }
//    }

    fun setData(){
        _homePopularMovie.postValue(createDummyPopularMovies())
        _homeRecentMovie.postValue(createDummyRecentMovies())
    }

    private fun createDummyPopularMovies(): MutableList<PopularMovieModel>{
        return mutableListOf(
            PopularMovieModel(
                originalTitle = "Spider man",
                image = R.drawable.movie_logo,
                voteAverage = 90.0,
                id = 1
            ),
            PopularMovieModel(
                originalTitle = "The Hunger Games",
                image = R.drawable.movie_logo,
                voteAverage = 80.0,
                id = 2
            )
        )
    }

    private fun createDummyRecentMovies(): MutableList<RecentMovieModel>{
        return mutableListOf(
            RecentMovieModel(
                original_title = "Spider man",
                image = R.drawable.movie_logo,
                vote_average = 90.0,
                id = 1
            ),
            RecentMovieModel(
                original_title = "The Hunger Games",
                image = R.drawable.movie_logo,
                vote_average = 80.0,
                id = 2
            ),
            RecentMovieModel(
                original_title = "Spongebob The Movie",
                image = R.drawable.movie_logo,
                vote_average = 100.0,
                id = 3
            )
        )
    }
}