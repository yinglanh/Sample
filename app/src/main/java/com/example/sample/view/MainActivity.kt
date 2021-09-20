package com.example.sample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.sample.MainViewModel
import com.example.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var myViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        myViewModel.userListLiveData.observe(this, {
            if (myViewModel.userListLiveData.value.isNullOrEmpty()) {
                myViewModel.fetchUsers()
            }
        })

        binding.viewModel = myViewModel
        binding.lifecycleOwner = this
    }
}