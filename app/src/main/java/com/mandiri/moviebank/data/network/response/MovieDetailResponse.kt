package com.mandiri.moviebank.data.network.response

import com.mandiri.moviebank.model.MovieDetailModel

data class MovieDetailResponse(
    val results: List<MovieDetailModel>,
    val total_results: Int
)