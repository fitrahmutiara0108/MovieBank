package com.mandiri.moviebank.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mandiri.moviebank.data.remote.network.api.ApiClient
import com.mandiri.moviebank.data.remote.repository.MovieRepository
import com.mandiri.moviebank.model.PopularMovieModel
import com.mandiri.moviebank.model.SearchMovieModel
import com.mandiri.moviebank.model.TopMovieModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeViewModel: ViewModel() {
    private val _homePopularMovie = MutableLiveData<List<PopularMovieModel>>()
    private val _homeTopMovie = MutableLiveData<List<TopMovieModel>>()
    private val _searchedMovie = MutableLiveData<List<SearchMovieModel>>()
    private val _progress = MutableLiveData<Boolean>()

    val homePopularMovie: LiveData<List<PopularMovieModel>> = _homePopularMovie
    val homeTopMovie: LiveData<List<TopMovieModel>> = _homeTopMovie
    val searchedMovie: LiveData<List<SearchMovieModel>> = _searchedMovie
    val progress: LiveData<Boolean> = _progress

    private val movieRepository = MovieRepository(ApiClient.movieApiService)

    fun setData(){
        _progress.postValue(true)
        try{
            viewModelScope.launch {
                _homePopularMovie.postValue(movieRepository.getPopularMovies().body()?.results)
                _homeTopMovie.postValue(movieRepository.getTopMovies().body()?.results)
            }
        }  catch(e: Exception){
            //error handling
        } finally {
            _progress.postValue(false)
        }

    }
    suspend fun searchDataByQuery(query: String){
        _progress.postValue(true)
        delay(100)
        try {
            viewModelScope.launch {
                _searchedMovie.postValue(movieRepository.getSearchedMovie(query).body()?.results)
            }
        } catch(e: Exception){
            //error handling
        } finally {
            _progress.postValue(false)
        }

    }
}