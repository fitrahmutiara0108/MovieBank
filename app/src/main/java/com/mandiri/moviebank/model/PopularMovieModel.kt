package com.mandiri.moviebank.model

import java.io.Serializable

data class PopularMovieModel(
    val originalTitle: String,
    val voteAverage: Double,
    val image: Int,
    val id: Int,
    val adult: Boolean = true,
    val backdropPath: String = "",
    val originalLanguage: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val video: Boolean = true,
    val voteCount: Int = 0,
    var index: Int = 0,
    val isRecent: Int = 0,
    val isLiked: Int = 0
) : Serializable
