package com.mandiri.moviebank.presentation.bookmark

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mandiri.moviebank.data.local.BookmarkEntity


@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmark")
    fun getAllBookmarks(): List<BookmarkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: BookmarkEntity)

    @Query("DELETE FROM bookmark WHERE id = :movieId")
    suspend fun deleteBookmark(movieId: Int)
}