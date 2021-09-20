package com.example.sample

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sample.adapter.RecyclerViewAdapter
import com.example.sample.adapter.RecyclerViewAdapterUser
import com.example.sample.network.Photo
import com.example.sample.network.User

@BindingAdapter("bindPhotos")
fun bindPhotos(recyclerView: RecyclerView, photos: List<Photo>?) {
    if (!photos.isNullOrEmpty()){
        val recyclerAdapter = RecyclerViewAdapter(photos)
        recyclerView.adapter = recyclerAdapter
    }
}

@BindingAdapter("bindUsers")
fun bindUsers(recyclerView: RecyclerView, users: List<User>?) {
    if (!users.isNullOrEmpty()){
        val recyclerAdapter = RecyclerViewAdapterUser(users)
        recyclerView.adapter = recyclerAdapter
    }
}

@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImageFromUrl(photo: String?){
    this.load(photo)
}

@BindingAdapter("bindText")
fun TextView.bindText(text: String?){
    this.text = text
}

