package com.mandiri.moviebank.data.network.api

import com.mandiri.moviebank.model.RecentMovieModel
import com.mandiri.moviebank.data.network.response.AuthorResponse
import com.mandiri.moviebank.data.network.response.Images
import com.mandiri.moviebank.data.network.response.MovieResponse
import com.mandiri.moviebank.data.network.response.SearchResponse
import com.mandiri.moviebank.data.network.response.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = "a0d35c93cf4be4650e17892fd16ab7a8"
    ): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "a0d35c93cf4be4650e17892fd16ab7a8"
    ): Response<RecentMovieModel>

    @GET("movie/{movie_id}/images")
    suspend fun getMovieImages(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "a0d35c93cf4be4650e17892fd16ab7a8"
    ): Images

    @GET("search/movie")
    suspend fun getSearchedMovie(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = "a0d35c93cf4be4650e17892fd16ab7a8"
    ): Response<SearchResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun getAuthorsById(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "a0d35c93cf4be4650e17892fd16ab7a8"
    ): Response<AuthorResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun getVideosById(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "a0d35c93cf4be4650e17892fd16ab7a8"
    ): Response<VideoResponse>
}
