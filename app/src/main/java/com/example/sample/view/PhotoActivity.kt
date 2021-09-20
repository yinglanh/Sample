package com.example.sample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.sample.MainViewModel
import com.example.sample.R
import com.example.sample.databinding.ActivityAlbumBindingImpl
import com.example.sample.databinding.ActivityPhotoBindingImpl
import com.example.sample.network.Photo

class PhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        var binding : ActivityPhotoBindingImpl = DataBindingUtil.setContentView(this,
            R.layout.activity_photo
        )

        val myPhoto = intent.extras?.get("photo") as Photo
        Log.e("PhotoActivity", "extra $myPhoto")

        binding.photo = myPhoto
    }
}