package com.mandiri.moviebank.model

import com.mandiri.moviebank.data.network.response.Genre
import com.mandiri.moviebank.data.network.response.ProductionCompany
import java.io.Serializable

data class MovieDetailModel(
    val original_title: String,
    val vote_average: Double,
    val image: Int,
    val actor_image: Int,
    val id: Int,
    val budget: Int,
    val genres: List<Genre>?,
    val imdb_id: String?,
    val release_date: String,
    val homepage: String = "",
    var isSaved: Boolean = false,
    val overview: String = "",
    val popularity: Double? = null,
    val poster_path: String? = null,
    val production_companies: List<ProductionCompany>? = null,
    val revenue: Long? = null,
    val runtime: Int = 0,
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = true,
    val vote_count: Int = 0,
    var index: Int = 0,
) : Serializable
