package com.example.sample

import android.graphics.Movie
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample.network.ApiClient
import com.example.sample.network.Photo
import com.example.sample.network.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainViewModel(private val repository: Repository = Repository(ApiClient.apiService)) :
    ViewModel() {

    private var _userListLiveData: MutableLiveData<User> = MutableLiveData()
    val userListLiveData: LiveData<User>
        get() = _userListLiveData
    private var _photoListLiveData: MutableLiveData<Photo> = MutableLiveData()
    val photoListLiveData: LiveData<Photo>
        get() = _photoListLiveData

    init {
        fetchUsers()
        fetchPhotos()
    }

    private fun fetchUsers() {
        Log.e("fetchUsers()", "")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val client = repository.getUsers()
                if (!client.isNullOrEmpty()){
                    for (u in client){
                        _userListLiveData.postValue(u)
                    }
                }
            } catch (throwable: Throwable) {
                Log.e("getUsers", throwable.toString())
                handleThrowable(throwable)
            }
        }
    }

    private fun fetchPhotos() {
        Log.e("fetchPhotos()", "")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val client2 = repository.getPhotos()
                if (!client2.isNullOrEmpty()){
                    for (u in client2){
                        _photoListLiveData.postValue(u)
                    }
                }
            } catch (throwable: Throwable) {
                Log.e("getPhotos", throwable.toString())
                handleThrowable(throwable)
            }
        }

    }

    private fun handleThrowable(throwable: Throwable) {
        when (throwable) {
            is IOException -> Log.e("handleThrowable", "NetworkError")
            is HttpException -> {
                val code = throwable.code()
                val errorMessage = throwable.message()
                Log.e("handleThrowable", "HttpException, code: $code, message: $errorMessage")
            }
            else -> {
                Log.e("handleThrowable", throwable.message.toString())
            }
        }
    }

}