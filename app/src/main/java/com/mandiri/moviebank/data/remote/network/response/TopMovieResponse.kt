package com.mandiri.moviebank.data.remote.network.response

import com.mandiri.moviebank.model.TopMovieModel

data class TopMovieResponse(
    val page: Int,
    val results: List<TopMovieModel>,
    val total_pages: Int,
    val total_results: Int
)