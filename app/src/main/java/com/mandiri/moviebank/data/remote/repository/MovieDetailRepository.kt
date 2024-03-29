package com.mandiri.moviebank.data.remote.repository

import com.mandiri.moviebank.data.remote.network.api.MovieApiService
import com.mandiri.moviebank.data.remote.network.response.AuthorResponse
import com.mandiri.moviebank.data.remote.network.response.Images
import com.mandiri.moviebank.data.remote.network.response.VideoResponse
import com.mandiri.moviebank.model.MovieDetailModel
import retrofit2.Response

class MovieDetailRepository(private val movieApiService: MovieApiService) {

    suspend fun getMovieById(movieId: Int): Response<MovieDetailModel> {
        val result= movieApiService.getMovieById(movieId)
        return result
//        return try {
//            val result= movieApiService.getMovieById(movieId)
//            result
//        }catch (e:Throwable){
//            Log.d("test", e.message?:"Unknown error")
//            Response.success(MovieDetailResponse(listOf(), 0))
//        }
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
