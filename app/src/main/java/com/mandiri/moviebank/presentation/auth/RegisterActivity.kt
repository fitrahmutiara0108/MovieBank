package com.mandiri.moviebank.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mandiri.moviebank.databinding.ActivityRegisterBinding
import com.mandiri.moviebank.helper.SharedPrefHelper
import com.mandiri.moviebank.presentation.home.HomeMainActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sharedPreferences: SharedPrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvRegisterToLogin.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener {
            handleRegister()
        }
    }

    private fun handleRegister() {

        with(binding) {
            val nameValue = etName.text.toString()
            val emailValue = etEmail.text.toString()
            val phoneValue = etPhoneNumber.text.toString()

            if (nameValue.isEmpty() || emailValue.isEmpty() || phoneValue.isEmpty()) {
                Toast.makeText(applicationContext, "Data can't be empty", Toast.LENGTH_SHORT)
                    .show()
            } else if (!isEmailValid(emailValue)){
                Toast.makeText(applicationContext, "Invalid email", Toast.LENGTH_SHORT).show()
            }
            else {
                sharedPreferences = SharedPrefHelper(getSharedPreferences("mypref", MODE_PRIVATE))
                sharedPreferences.saveProfileData(nameValue, emailValue, phoneValue)

                val intent = Intent(this@RegisterActivity, HomeMainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        val emailRegex = Regex("^\\S+@\\S+\\.\\S+\$")
        return email.matches(emailRegex)
    }

    companion object {
        const val KEY_NAME_VALUE = "key_name_value"
        const val KEY_EMAIL_VALUE = "key_email_value"
        const val KEY_PHONE_NUMBER_VALUE = "key_phone_number_value"
    }
}