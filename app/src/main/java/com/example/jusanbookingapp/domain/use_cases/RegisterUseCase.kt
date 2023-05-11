package com.example.jusanbookingapp.domain.use_cases

import com.example.jusanbookingapp.domain.repositories.AuthRepository

class RegisterUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke(username : String, email : String, password : String) {
        authRepository.register(username, email, password)
    }
}