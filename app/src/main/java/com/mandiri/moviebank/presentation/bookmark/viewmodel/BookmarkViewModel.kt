package com.mandiri.moviebank.presentation.bookmark.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mandiri.moviebank.model.BookmarkEntity
import com.mandiri.moviebank.model.MovieDetailModel
import com.mandiri.moviebank.data.local.AppDatabase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BookmarkViewModel (
    application: Application
) : ViewModel() {
    private val _savedMovies = MutableLiveData<List<BookmarkEntity>>()
    val savedMovies: LiveData<List<BookmarkEntity>> = _savedMovies

    private val _isMovieSaved = MutableLiveData<Boolean>()
    val isMovieSaved: LiveData<Boolean> = _isMovieSaved

    private val bookmarkDao = AppDatabase.getDatabase(application).bookmarkDao()

    init {
        loadSavedMovies()
    }

    fun loadSavedMovies() {
        viewModelScope.launch {
            val bookmark = bookmarkDao.getAllBookmarks()
            Log.d("tiara", "Bookmark $bookmark")
            _savedMovies.value = bookmark
            Log.d("tiara", "saved movies ${savedMovies.value}")
        }
    }

    fun isMovieSaved(movieId: Int) {
        Log.d("tiara", "isMovieSaved")

        // Use Dispatchers.IO to perform database operations in the background
        viewModelScope.launch {
            loadSavedMovies()
            // Delay to wait for the value to be updated
            delay(100)

            val savedMoviesData = savedMovies.value
            Log.d("tiara", "list saved ${savedMovies.value}")
            val result = savedMoviesData?.any { it.id == movieId } == true
            Log.d("tiara", result.toString())
            _isMovieSaved.postValue(result)
        }
    }


    fun saveMovie(movie: MovieDetailModel) {
        viewModelScope.launch {
            val bookmarkEntity = movie.toBookmarkEntity()
            bookmarkDao.insertBookmark(bookmarkEntity)
            isMovieSaved(movie.id)
            Log.d("BookmarkViewModel", "Movie saved: ${movie.title}")
        }
    }

    fun unsaveMovie(movieId: Int, movie: MovieDetailModel) {
        viewModelScope.launch {
            bookmarkDao.deleteBookmark(movieId)
            isMovieSaved(movieId)
        }
    }

    private fun MovieDetailModel.toBookmarkEntity(): BookmarkEntity {
        return BookmarkEntity(
            id = this.id,
            original_title = this.original_title,
            vote_average = this.vote_average,
            poster_path = this.poster_path,
            isSaved = false,
            index = 0
        )
    }
}
