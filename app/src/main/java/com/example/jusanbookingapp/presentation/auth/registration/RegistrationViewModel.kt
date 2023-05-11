package com.example.jusanbookingapp.presentation.auth.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jusanbookingapp.constants.AppPreferences
import com.example.jusanbookingapp.domain.use_cases.RegisterUseCase
import kotlinx.coroutines.launch

class RegistrationViewModel(private val registerUseCase: RegisterUseCase) : ViewModel() {
    lateinit var email : String
    lateinit var username : String
    lateinit var password : String

    fun onSubmit() {
        viewModelScope.launch {
            registerUseCase(username = username, email = email, password = password)
            AppPreferences.username = username
            AppPreferences.userEmail = email
            AppPreferences.password = password
        }
    }

}