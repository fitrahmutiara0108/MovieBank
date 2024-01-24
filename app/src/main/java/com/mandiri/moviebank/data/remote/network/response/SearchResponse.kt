package com.mandiri.moviebank.data.remote.network.response

import com.mandiri.moviebank.model.SearchMovieModel

data class SearchResponse(
    val page: Int,
    val results: List<SearchMovieModel>,
    val total_pages: Int,
    val total_results: Int
)