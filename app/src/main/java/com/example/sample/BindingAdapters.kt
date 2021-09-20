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

@BindingAdapter(value = ["bind:photos", "bind:id"], requireAll=true)
fun bindPhotos(recyclerView: RecyclerView, photos: List<Photo>?, id:Int) {
    if (!photos.isNullOrEmpty()){
        val returnList = mutableListOf<Photo>()
        for (photo in photos){
            if (photo.albumId == id.toString()){
                returnList.add(photo)
            }
        }
        val recyclerAdapter = RecyclerViewAdapter(returnList)
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

