package com.example.wampserverlesson

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainApi: MainApi
): ViewModel() {
    val userList = mutableStateOf(emptyList<User>())

    init {
        viewModelScope.launch {
            userList.value = mainApi.getAllUsers()
        }
    }

    fun saveUser(user: User) = viewModelScope.launch {
        mainApi.saveUser(user)
        userList.value = mainApi.getAllUsers()
    }

    fun uploadImage(imageData: ImageData) = viewModelScope.launch {
        val imageResponse = mainApi.uploadImage(imageData)
        Log.d("MyLog", "Image URL: ${imageResponse.url}")
        Log.d("MyLog", "Image upload message: ${imageResponse.message}")
    }

}