package com.mandiri.moviebank.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mandiri.moviebank.databinding.ActivityDetailMovieBinding
import com.mandiri.moviebank.model.MovieDetailModel

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var data: MovieDetailModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

//        binding.tvMovieTitle = data.title
    }

}