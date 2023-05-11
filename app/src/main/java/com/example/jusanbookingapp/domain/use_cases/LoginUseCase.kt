package com.example.jusanbookingapp.domain.use_cases

import com.example.jusanbookingapp.domain.models.User
import com.example.jusanbookingapp.domain.repositories.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke(username : String,  password : String) : User {
        return authRepository.login(username, password)
    }
}