package com.mandiri.moviebank.presentation.bookmark

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mandiri.moviebank.presentation.bookmark.viewmodel.BookmarkViewModel

class BookmarkViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
     override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookmarkViewModel::class.java)) {
            return BookmarkViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
