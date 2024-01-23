package com.mandiri.moviebank.presentation.bookmark.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mandiri.moviebank.data.local.BookmarkEntity
import com.mandiri.moviebank.model.MovieDetailModel
import com.mandiri.moviebank.presentation.bookmark.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookmarkViewModel (
    application: Application
) : ViewModel() {
    private val _savedMovies = MutableLiveData<List<BookmarkEntity>>()
    val savedMovies: LiveData<List<BookmarkEntity>> = _savedMovies

    private val _isMovieSaved = MutableLiveData<Boolean>()
    val isMovieSaved: LiveData<Boolean> = _isMovieSaved

    private val bookmarkDao = AppDatabase.getDatabase(application).bookmarkDao()

    fun loadSavedMovies() {
        viewModelScope.launch {
            _savedMovies.postValue(withContext(Dispatchers.IO) {
                bookmarkDao.getAllBookmarks()
            })
        }
    }

    suspend fun isMovieSaved(movieId: Int): Boolean {
        // Use Dispatchers.IO to perform database operations in the background
        return withContext(Dispatchers.IO) {
            val savedMovies = _savedMovies.value
            savedMovies?.any { it.id == movieId } == true
        }
    }

    suspend fun saveMovie(movie: MovieDetailModel) {
        withContext(Dispatchers.IO) {
            val bookmarkEntity = movie.toBookmarkEntity()
            bookmarkDao.insertBookmark(bookmarkEntity)
            loadSavedMovies()
        }
    }

    suspend fun unsaveMovie(movieId: Int, movie: MovieDetailModel) {
        withContext(Dispatchers.IO) {
            bookmarkDao.deleteBookmark(movieId)
            loadSavedMovies()
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
