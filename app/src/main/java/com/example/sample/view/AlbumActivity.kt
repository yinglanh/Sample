package com.example.sample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.sample.MainViewModel
import com.example.sample.R
import com.example.sample.databinding.ActivityAlbumBindingImpl

class AlbumActivity : AppCompatActivity() {
    private lateinit var myViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        var binding: ActivityAlbumBindingImpl = DataBindingUtil.setContentView(
            this,
            R.layout.activity_album
        )

        val albumId = intent.extras?.get("albumId") as Int
        Log.e("AlbumActivity", "extra $albumId")

        myViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        myViewModel.fetchPhotos()
        myViewModel.photoListLiveData.observe(this, {
            if (myViewModel.photoListLiveData.value.isNullOrEmpty()) {
                myViewModel.fetchPhotos()
            } else {
                Log.e("myViewModellist", "${myViewModel.photoListLiveData.value}")
                binding.photoList = myViewModel.photoListLiveData.value
            }
        })

        binding.albumId = albumId
    }
}