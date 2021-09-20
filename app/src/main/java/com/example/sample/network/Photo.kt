package com.example.sample.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Photo(
    @Expose
    @SerializedName("id")
    val id: String,
    @Expose
    @SerializedName("albumId")
    val albumId: String,
    @Expose
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("url")
    val url: String,
    @Expose
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
)




