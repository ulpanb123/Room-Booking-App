package com.example.jusanbookingapp.domain.repositories

import com.example.jusanbookingapp.domain.models.User

interface AuthRepository {

    suspend fun register(username : String, email : String, password : String)

    suspend fun login(username: String, password: String) : User
}