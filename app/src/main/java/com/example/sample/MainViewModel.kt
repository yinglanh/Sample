package com.example.sample

import android.util.Log
import androidx.databinding.PropertyChangeRegistry
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

    private var _userListLiveData = MutableLiveData<List<User>>(emptyList())
    private var _photoListLiveData = MutableLiveData<List<Photo>>(emptyList())

    val userListLiveData: LiveData<List<User>>
        get() = _userListLiveData
    val photoListLiveData: LiveData<List<Photo>>
        get() = _photoListLiveData


    init {
        fetchUsers()
        fetchPhotos()
    }

    fun fetchUsers() {
        Log.e("fetchUsers()", "")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val client = repository.getUsers()
                _userListLiveData.postValue(client)
            } catch (throwable: Throwable) {
                Log.e("getUsers", throwable.toString())
                handleThrowable(throwable)
            }
        }
    }

    fun fetchPhotos() {
        Log.e("fetchPhotos()", "")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val client2 = repository.getPhotos()
                _photoListLiveData.postValue(client2)
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