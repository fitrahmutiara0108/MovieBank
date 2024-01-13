package com.mandiri.moviebank.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mandiri.moviebank.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(){
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}