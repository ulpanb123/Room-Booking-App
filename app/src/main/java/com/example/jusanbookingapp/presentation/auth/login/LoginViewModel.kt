package com.example.jusanbookingapp.presentation.auth.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jusanbookingapp.constants.AppPreferences
import com.example.jusanbookingapp.domain.use_cases.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {
    lateinit var username : String
    lateinit var password : String

    private val _isLoggedIn = MutableLiveData<Boolean> ()
    val isLoggedIn : LiveData<Boolean> = _isLoggedIn

    init {
        _isLoggedIn.value = false
    }
    fun onLogin() {
        viewModelScope.launch {
            val response = loginUseCase(username = username, password = password)
            AppPreferences.accessToken = response.token
            _isLoggedIn.postValue(true)

            Log.e("Viewmodel", response.token)

            AppPreferences.userId = response.userId
        }
    }
}