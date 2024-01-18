package com.mandiri.moviebank.presentation.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mandiri.moviebank.data.network.api.ApiClient
import com.mandiri.moviebank.data.network.response.Backdrop
import com.mandiri.moviebank.data.network.response.Cast
import com.mandiri.moviebank.data.repository.MovieDetailRepository
import com.mandiri.moviebank.model.MovieDetailModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(): ViewModel() {
    private val _fullMovie = MutableLiveData<List<MovieDetailModel>>()
    private val _progress = MutableLiveData<Boolean>()
    private val _authorsList = MutableLiveData<List<Cast>>()
    private val _images = MutableLiveData<List<Backdrop>>()
    private val _errorMessage = MutableLiveData<String>()

    val fullMovie: LiveData<List<MovieDetailModel>> = _fullMovie
    val progress: LiveData<Boolean> = _progress
    val authorsList: LiveData<List<Cast>> = _authorsList
    val images: LiveData<List<Backdrop>> = _images
    val errorMessage: LiveData<String> = _errorMessage

    private val movieRepository = MovieDetailRepository(ApiClient.movieApiService)

    fun setData(id: Int) {
        viewModelScope.launch {
            _authorsList.postValue(movieRepository.getAuthorsById(id).body()?.cast)
            _images.postValue(movieRepository.getMovieImages(id).backdrops)
            _fullMovie.postValue(movieRepository.getMovieById(id).body()?.results)
        }
    }
}
//    fun loadAuthorsDataById(id: Int) {
//        repo.getAuthorsById(id).onEach {
//            it.onSuccess {
//                _authorsList.value = it
//            }
//
//            it.onFailure {
//                _errorMessage.value = it.message.toString()
//            }
//        }.launchIn(viewModelScope)
//    }
//
//    fun loadImagesDataById(id: Int) {
//        repo.getImagesById(id).onEach {
//            it.onSuccess {
//                _images.value = it
//
//            }
//        }.launchIn(viewModelScope)
//    }
//
//    fun updateData(movieEntity: MovieDetailModel) {
//        viewModelScope.launch {
//            repo.updateMovieData(movieEntity).onEach {
//
//            }.launchIn(viewModelScope)
//        }
//    }
//
//    fun addData(movieEntity: MovieDetailModel) {
//        repo.addMovieData(movieEntity).onEach {
//        }.launchIn(viewModelScope)
//    }
//    fun loadDataById(id: Int) {
//        _progress.value = true
//        var lastIndex = 0
//        var local:MovieDetailModel?=null
//        repo.localDataById(id).onEach { it ->
//            it.onSuccess { local1 ->
//                local =local1
//                repo.getLastIndex().onEach {
//                    val index = it ?: -1
//                    lastIndex = if (local1 != null) {
//                        if (index == -1) 0 else if (index == local1.index) index else index!! + 1
//                    } else {
//                        if (index == -1) 0 else index!! + 1
//                    }
//
//
//                }.launchIn(viewModelScope)
//            }
//
//        }.launchIn(viewModelScope)
//
//        if (hasConnection()) {
//            repo.getMovieDataById(id).onEach {
//                it.onSuccess { data ->
//                    _progress.value = false
//                    if (local == null) {
//                        data.index = lastIndex
//                    } else {
//                        data.index = lastIndex
//                        data.isSaved = local!!.isSaved
//                    }
//                    addData(data)
//
//                    _fullMovie.value = listOf(data)
//                }
//
//                it.onFailure {
//                    _progress.value = false
//                    _errorMessage.value = it.message.toString()
//                }
//            }.launchIn(viewModelScope)
//        }else{
//            repo.localDataById(id).onEach {
//                it.onSuccess {local ->
//                    if (local != null) {
//                        local.index = lastIndex
//                        _fullMovie.value = listOf(local)!!
//
//                        addData(local)
//                    } else {
//                        _errorMessage.value="No Internet Connection"
//                    }
//                }
//            }
//
//        }
//    }


