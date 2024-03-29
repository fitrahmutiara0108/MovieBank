package com.mandiri.moviebank.presentation.auth

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.mandiri.moviebank.databinding.ActivityLoginBinding
import com.mandiri.moviebank.helper.SharedPrefHelper
import com.mandiri.moviebank.presentation.home.HomeMainActivity
import java.util.UUID

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPrefHelper: SharedPrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val sharedPreferences: SharedPreferences = getSharedPreferences("mypref", MODE_PRIVATE)
        sharedPrefHelper = SharedPrefHelper(sharedPreferences)
        setContentView(binding.root)

        checkTokenAvailability()
        handleLogin()
    }

    private fun handleVisibility(view: View, isVisible: Boolean) {
        view.isVisible = isVisible
    }

    private fun handleNavigation() {
        val intent = Intent(this, HomeMainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun observeLoginSuccess(success: Boolean) {
        if (success) {
            Toast.makeText(applicationContext, "Berhasil", Toast.LENGTH_SHORT).show()
            handleNavigation()
        } else {
            Toast.makeText(this@LoginActivity, "Gagal", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleLogin() {
        binding.apply {
            btnLogin.setOnClickListener {
                val enteredPassword = etPassword.text.toString()
                val enteredEmail = etEmail.text.toString()

                if(isEmailValid(enteredEmail)){
                    login(enteredEmail, enteredPassword)
                } else {
                    Toast.makeText(this@LoginActivity, "Invalid email format", Toast.LENGTH_SHORT).show()
                }

            }

            tvLoginToRegister.setOnClickListener {
                handleTo(RegisterActivity::class.java)
            }
        }
    }

    private fun login(email: String, password: String) {
        val correctPassword = "user1234"
        val correctEmail = "user@gmail.com"

        if (password == correctPassword && email == correctEmail) {
            val dummyToken: String = UUID.randomUUID().toString()
            sharedPrefHelper.saveToken(dummyToken)
            observeLoginSuccess(true)
        } else {
            observeLoginSuccess(false)
        }
    }

    private fun checkTokenAvailability() {
        if (isTokenAvailable()) {
            handleNavigation()
        }
    }

    private fun isTokenAvailable(): Boolean {
        val token = sharedPrefHelper.getToken()
        return token.isNotEmpty()
    }

    private fun handleTo(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }

    private fun isEmailValid(email: String): Boolean {
        val emailRegex = Regex("^\\S+@\\S+\\.\\S+\$")
        return email.matches(emailRegex)
    }
}
