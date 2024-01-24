package com.mandiri.moviebank.data.remote.repository

import com.mandiri.moviebank.data.remote.network.response.Backdrop
import com.mandiri.moviebank.data.remote.network.response.Cast
import com.mandiri.moviebank.model.MovieDetailModel
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepositoryInterface {
    fun getMovieDataById(id:Int): Flow<Result<MovieDetailModel>>
    fun updateMovieData(movieEntity: MovieDetailModel): Flow<Result<Unit>>
    fun addMovieData(movieEntity: MovieDetailModel): Flow<Result<Unit>>
    fun getImagesDataById(id:Int): Flow<Result<List<Backdrop>>>
    fun getAuthorsDataById(id:Int): Flow<Result<List<Cast>>>
    fun addBookMarkData(lastMovieEntity: MovieDetailModel): Flow<Result<Unit>>
    fun addLastRecentData(lastMovieEntity: MovieDetailModel): Flow<Result<Unit>>
    fun updateLastRecentData(lastMovieEntity: MovieDetailModel): Flow<Result<Unit>>
    fun localDataById(id: Int): Flow<Result<MovieDetailModel?>>
    fun getLastIndex(): Flow<Int?>

}