package com.mandiri.moviebank.helper

import android.content.SharedPreferences
import com.mandiri.moviebank.presentation.auth.RegisterActivity

class SharedPrefHelper (private val sharedPreferences: SharedPreferences) {

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
    }

    fun getToken(): String {
        return sharedPreferences.getString(TOKEN_KEY, "") ?: ""
    }

    fun clearDataPref() {
        sharedPreferences.edit().remove(TOKEN_KEY).apply()
    }

    fun saveProfileData(name: String, email: String, phoneNumber: String) {
        sharedPreferences.edit().putString(RegisterActivity.KEY_NAME_VALUE, name).apply()
        sharedPreferences.edit().putString(RegisterActivity.KEY_EMAIL_VALUE, email).apply()
        sharedPreferences.edit().putString(RegisterActivity.KEY_PHONE_NUMBER_VALUE, phoneNumber).apply()
    }

    fun getProfileData(): Triple<String?, String?, String?> {
        val name = sharedPreferences.getString(RegisterActivity.KEY_NAME_VALUE, null)
        val email = sharedPreferences.getString(RegisterActivity.KEY_EMAIL_VALUE, null)
        val phoneNumber = sharedPreferences.getString(RegisterActivity.KEY_PHONE_NUMBER_VALUE, null)
        return Triple(name, email, phoneNumber)
    }

    companion object {
        private const val TOKEN_KEY = "token_key"
        private const val SHARED_PREF_NAME = "mypref"
    }
}
