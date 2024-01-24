package com.mandiri.moviebank.data.remote.network.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    val provideOkHttpClient =
        OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            connectTimeout(300, TimeUnit.SECONDS)
            readTimeout(300, TimeUnit.SECONDS)
        }.build()

    val movieApiService: com.mandiri.moviebank.data.remote.network.api.MovieApiService by lazy {
        Retrofit.Builder()
            .baseUrl(com.mandiri.moviebank.data.remote.network.api.ApiClient.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(com.mandiri.moviebank.data.remote.network.api.ApiClient.provideOkHttpClient)
            .build()
            .create(com.mandiri.moviebank.data.remote.network.api.MovieApiService::class.java)
    }
}
