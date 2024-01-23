package com.mandiri.moviebank.data.network.response

import com.mandiri.moviebank.model.PopularMovieModel

data class MovieResponse(
    val page: Int,
    val results: List<PopularMovieModel>,
    val total_pages: Int,
    val total_results: Int
)