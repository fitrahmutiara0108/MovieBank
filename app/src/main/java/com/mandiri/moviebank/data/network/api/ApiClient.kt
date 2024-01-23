package com.mandiri.moviebank.data.network.api

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

    val movieApiService: MovieApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient)
            .build()
            .create(MovieApiService::class.java)
    }
}
