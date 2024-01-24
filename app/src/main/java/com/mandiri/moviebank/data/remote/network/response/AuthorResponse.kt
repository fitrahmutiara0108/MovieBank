package com.mandiri.moviebank.data.remote.network.response

data class AuthorResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)