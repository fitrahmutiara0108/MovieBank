package com.mandiri.moviebank.model

import com.mandiri.moviebank.data.remote.network.response.Genre
import com.mandiri.moviebank.data.remote.network.response.ProductionCompany
import java.io.Serializable

data class SearchMovieModel(
    val original_title: String,
    val vote_average: Double,
    val image: Int,
    val adult: Boolean,
    val backdrop_path: String?,
    val budget: Int,
    val genres: List<Genre>?,
    val homepage: String,
    val id: Int,
    val imdb_id: String?,
    val original_language: String,
    val overview: String,
    val popularity: Double?,
    val poster_path: String?,
    val production_companies: List<ProductionCompany>?,
    val release_date: String,
    val revenue: Long?,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_count: Int,
    var isSaved: Boolean,
    var index: Int,
) : Serializable
