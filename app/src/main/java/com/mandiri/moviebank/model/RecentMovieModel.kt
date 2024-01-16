package com.mandiri.moviebank.model

import com.mandiri.moviebank.data.network.response.Genre
import com.mandiri.moviebank.data.network.response.ProductionCompany
import java.io.Serializable

data class RecentMovieModel(
    val original_title: String,
    val vote_average: Double,
    val image: Int,
    val adult: Boolean = true,
    val backdrop_path: String? = null,
    val budget: Int = 0,
    val genres: List<Genre>? = null,
    val homepage: String = "",
    val id: Int,
    val imdb_id: String? = null,
    val original_language: String = "",
    val overview: String = "",
    val popularity: Double? = null,
    val poster_path: String? = null,
    val production_companies: List<ProductionCompany>? = null,
    val release_date: String = "",
    val revenue: Long? = null,
    val runtime: Int = 0,
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = true,
    val vote_count: Int = 0,
    var isSaved: Boolean = false,
    var index: Int = 0,
): Serializable

