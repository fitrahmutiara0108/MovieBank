package com.mandiri.moviebank.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mandiri.moviebank.data.network.api.ApiClient
import com.mandiri.moviebank.data.repository.MovieRepository
import com.mandiri.moviebank.model.PopularMovieModel
import com.mandiri.moviebank.model.SearchMovieModel
import com.mandiri.moviebank.model.TopMovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {
    private val _homePopularMovie = MutableLiveData<List<PopularMovieModel>>()
    private val _homeTopMovie = MutableLiveData<List<TopMovieModel>>()
    private val _searchedMovie = MutableLiveData<List<SearchMovieModel>>()

    val homePopularMovie: LiveData<List<PopularMovieModel>> = _homePopularMovie
    val homeTopMovie: LiveData<List<TopMovieModel>> = _homeTopMovie
    val searchedMovie: LiveData<List<SearchMovieModel>> = _searchedMovie

    private val movieRepository = MovieRepository(ApiClient.movieApiService)

    fun setData(){
        viewModelScope.launch {
            _homePopularMovie.postValue(movieRepository.getPopularMovies().body()?.results)
            _homeTopMovie.postValue(movieRepository.getTopMovies().body()?.results)
        }
    }
    suspend fun searchDataByQuery(query: String){
        viewModelScope.launch {
            _searchedMovie.postValue(movieRepository.getSearchedMovie(query).body()?.results)
        }
    }
}