package com.mandiri.moviebank.presentation.movie.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mandiri.moviebank.data.network.api.ApiClient
import com.mandiri.moviebank.data.network.response.Backdrop
import com.mandiri.moviebank.data.network.response.Cast
import com.mandiri.moviebank.data.network.response.Result
import com.mandiri.moviebank.data.repository.MovieDetailRepository
import com.mandiri.moviebank.model.MovieDetailModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(): ViewModel() {
    private val _fullMovie = MutableLiveData<MovieDetailModel>()
    private val _progress = MutableLiveData<Boolean>()
    private val _authorsList = MutableLiveData<List<Cast>>()
    private val _images = MutableLiveData<List<Backdrop>>()
    private val _errorMessage = MutableLiveData<String>()
    private val _video = MutableLiveData<List<Result>>()

    val fullMovie: LiveData<MovieDetailModel> = _fullMovie
    val progress: LiveData<Boolean> = _progress
    val authorsList: LiveData<List<Cast>> = _authorsList
    val images: LiveData<List<Backdrop>> = _images
    val errorMessage: LiveData<String> = _errorMessage
    val video: LiveData<List<Result>> = _video

    private val movieRepository = MovieDetailRepository(ApiClient.movieApiService)

    fun setData(id: Int) {
        viewModelScope.launch {
            val getAuthor = movieRepository.getAuthorsById(id).body()?.cast
            val getImage = movieRepository.getMovieImages(id).backdrops
            val getDetailMovie = movieRepository.getMovieById(id).body()
            _authorsList.postValue(getAuthor?: listOf())
            _images.postValue(getImage)
            if (getDetailMovie == null)
                Log.d("ini test", "error Move Detail VM")
            else
                _fullMovie.postValue(getDetailMovie!!)
            _video.postValue(movieRepository.getVideosById(id).body()?.results)
        }
    }
}


