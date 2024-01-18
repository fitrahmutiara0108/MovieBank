package com.mandiri.moviebank.data.repository

import android.util.Log
import com.mandiri.moviebank.data.network.api.MovieApiService
import com.mandiri.moviebank.data.network.response.AuthorResponse
import com.mandiri.moviebank.data.network.response.Images
import com.mandiri.moviebank.data.network.response.MovieDetailResponse
import com.mandiri.moviebank.data.network.response.VideoResponse
import retrofit2.Response

class MovieDetailRepository(private val movieApiService: MovieApiService) {

    suspend fun getMovieById(movieId: Int): Response<MovieDetailResponse> {
        val res = movieApiService.getMovieById(movieId)
        Log.d("ini test", "repository ${res.body()}")
        return res
    }

    suspend fun getMovieImages(movieId: Int): Images {
        return movieApiService.getMovieImages(movieId)
    }

    suspend fun getAuthorsById(movieId: Int): Response<AuthorResponse> {
        return movieApiService.getAuthorsById(movieId)
    }

    suspend fun getVideosById(movieId: Int): Response<VideoResponse> {
        return movieApiService.getVideosById(movieId)
    }
}
