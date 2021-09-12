package com.example.sample
import com.example.sample.network.ApiService

class Repository(private val apiService: ApiService) {

    suspend fun getUsers() = apiService.fetchUsers()
    suspend fun getPhotos() = apiService.fetchPhotos()
}