package com.mandiri.moviebank.data.remote.network.response

data class VideoResponse(
    val id: Int,
    val results: List<Result>
)