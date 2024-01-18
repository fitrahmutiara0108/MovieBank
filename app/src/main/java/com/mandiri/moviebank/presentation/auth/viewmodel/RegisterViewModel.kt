package com.mandiri.moviebank.presentation.auth.viewmodel

import androidx.lifecycle.ViewModel
import com.mandiri.moviebank.helper.SharedPrefHelper

class RegisterViewModel(private val sharedPrefHelper: SharedPrefHelper) : ViewModel() {

    fun register(name: String, email: String, phoneNumber: String) {
        sharedPrefHelper.saveProfileData(name, email, phoneNumber)
    }
}
