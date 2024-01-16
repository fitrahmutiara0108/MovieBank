package com.mandiri.moviebank.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mandiri.moviebank.data.network.api.MovieApi
import com.mandiri.moviebank.data.network.response.AuthorResponse
import com.mandiri.moviebank.data.network.response.Images
import com.mandiri.moviebank.data.network.response.MovieResponse
import com.mandiri.moviebank.data.network.response.SearchResponse
import com.mandiri.moviebank.data.network.response.VideoResponse
import com.mandiri.moviebank.model.RecentMovieModel
import com.mandiri.moviebank.model.MovieModel
import retrofit2.Response

class MovieRepository(private val movieApi: MovieApi) {

    private val _popularMovies = MutableLiveData<List<MovieModel>>()
    val popularMovies: LiveData<List<MovieModel>> get() = _popularMovies

    private val _recentMovies = MutableLiveData<List<MovieModel>>()
    val recentMovies: LiveData<List<MovieModel>> get() = _recentMovies

    suspend fun getPopularMovies(): Response<MovieResponse> {
        return movieApi.getPopularMovies()
    }

    suspend fun getRecentMovies(): Response<MovieResponse> {
        return movieApi.getPopularMovies()
    }

    suspend fun getMovieById(movieId: Int): Response<RecentMovieModel> {
        return movieApi.getMovieById(movieId)
    }

    suspend fun getMovieImages(movieId: Int): Images {
        return movieApi.getMovieImages(movieId)
    }

    suspend fun getSearchedMovie(query: String): Response<SearchResponse> {
        return movieApi.getSearchedMovie(query)
    }

    suspend fun getAuthorsById(movieId: Int): Response<AuthorResponse> {
        return movieApi.getAuthorsById(movieId)
    }

    suspend fun getVideosById(movieId: Int): Response<VideoResponse> {
        return movieApi.getVideosById(movieId)
    }
}
