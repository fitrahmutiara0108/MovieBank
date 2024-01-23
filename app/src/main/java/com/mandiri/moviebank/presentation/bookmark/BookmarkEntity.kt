package com.mandiri.moviebank.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class BookmarkEntity(
    @PrimaryKey val id: Int,
    val original_title: String,
    val vote_average: Double,
    val poster_path: String?,
    val isSaved: Boolean,
    val index: Int
)
