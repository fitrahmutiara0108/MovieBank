package com.mandiri.moviebank.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mandiri.moviebank.data.network.api.ApiClient
import com.mandiri.moviebank.model.MovieModel
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val repository = MovieRepository(ApiClient.movieApi)

    val popularMovies: LiveData<List<MovieModel>> = repository.popularMovies
    val recentMovies: LiveData<List<MovieModel>> = repository.recentMovies

    fun fetchPopularMovies() {
        viewModelScope.launch {
            repository.getPopularMovies()
        }
    }

    fun fetchRecentMovies() {
        viewModelScope.launch {
            repository.getRecentMovies()
        }
    }
}

