package com.example.sample.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object ApiClient {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val retrofit: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    val apiService: ApiService by lazy {
        retrofit
            .build()
            .create(ApiService::class.java)
    }
}

interface ApiService {
    @GET("users")
    suspend fun fetchUsers(): List<User>

    @GET("photos")
    suspend fun fetchPhotos(): List<Photo>
}