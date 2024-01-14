package com.mandiri.moviebank.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mandiri.moviebank.databinding.FragmentProfileBinding
import com.mandiri.moviebank.presentation.home.RegisterActivity.Companion.KEY_EMAIL_VALUE
import com.mandiri.moviebank.presentation.home.RegisterActivity.Companion.KEY_NAME_VALUE
import com.mandiri.moviebank.presentation.home.RegisterActivity.Companion.KEY_PHONE_NUMBER_VALUE

class ProfileActivity: AppCompatActivity() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(KEY_NAME_VALUE)
        val email = intent.getStringExtra(KEY_EMAIL_VALUE)
        val phone = intent.getStringExtra(KEY_PHONE_NUMBER_VALUE)

        binding.tvName.text = name
        binding.tvEmail.text = email
        binding.tvPhoneNumber.text = phone
    }

}