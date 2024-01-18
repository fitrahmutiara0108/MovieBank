package com.mandiri.moviebank.data.repository

import com.mandiri.moviebank.data.network.api.MovieApiService
import com.mandiri.moviebank.data.network.response.MovieResponse
import com.mandiri.moviebank.data.network.response.SearchResponse
import com.mandiri.moviebank.data.network.response.TopMovieResponse
import retrofit2.Response

class MovieRepository(private val movieApiService: MovieApiService) {

    suspend fun getPopularMovies(): Response<MovieResponse> {
        return try {
             movieApiService.getPopularMovies()
        }catch (e:Throwable){
            Response.success(MovieResponse(0, listOf(), 0,0))
        }
    }

    suspend fun getTopMovies(): Response<TopMovieResponse> {
        return movieApiService.getTopRatedMovies()
    }

    suspend fun getSearchedMovie(query: String): Response<SearchResponse> {
        return movieApiService.getSearchedMovie(query)
    }


}
