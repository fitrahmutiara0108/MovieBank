package com.mandiri.moviebank.presentation.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mandiri.moviebank.helper.SharedPrefHelper
import java.util.UUID

class LoginViewModel(private val sharedPrefHelper: SharedPrefHelper) : ViewModel() {

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    fun login(email: String, password: String) {
        val correctPassword = "user1234"
        val correctEmail = "user@gmail.com"

        if (password == correctPassword && email == correctEmail) {
            val dummyToken: String = UUID.randomUUID().toString()
            sharedPrefHelper.saveToken(dummyToken)
            _loginSuccess.value = true
        } else {
            _loginSuccess.value = false
        }
    }
}
