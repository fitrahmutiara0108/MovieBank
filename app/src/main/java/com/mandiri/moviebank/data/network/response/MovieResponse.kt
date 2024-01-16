package com.mandiri.moviebank.data.network.response

data class MovieResponse(
    val page: Int,
//    val results: List<MovieEntity>,
    val total_pages: Int,
    val total_results: Int
)