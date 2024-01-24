package com.mandiri.moviebank.data.remote.network.response

data class Images(
    val backdrops: List<Backdrop>,
    val id: Int,
    val logos: List<Logo>,
    val posters: List<Poster>
)