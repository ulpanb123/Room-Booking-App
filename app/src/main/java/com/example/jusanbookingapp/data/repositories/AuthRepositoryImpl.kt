package com.example.jusanbookingapp.data.repositories

import android.util.Log
import com.example.jusanbookingapp.data.models.LoginRequest
import com.example.jusanbookingapp.data.models.RegisterRequest
import com.example.jusanbookingapp.data.network.ApiService
import com.example.jusanbookingapp.domain.models.User
import com.example.jusanbookingapp.domain.repositories.AuthRepository
import retrofit2.HttpException

class AuthRepositoryImpl(private val api : ApiService) : AuthRepository {
    override suspend fun register(username: String, email: String, password: String) {
        val request = RegisterRequest(username = username, email = email, password = password)
        try {
            // Make your Retrofit API call
            val response = api.registerUser(request)

            // Process the successful response
            // ...
        } catch (e: HttpException) {
            if (e.code() == 400) {
                val errorBody = e.response()?.errorBody()?.string()
                // Handle the error response
                // ...
                Log.e("ERROR", errorBody.toString())
            } else {
                // Handle other HTTP status codes
                // ...
            }
        } catch (e: Throwable) {
            // Handle other exceptions
            // ...
        }
    }

    override suspend fun login(username: String, password: String): User {
        val request = LoginRequest(username = username, password = password)
        val response = api.login(request)

        return User(token = response.token, userId = response.id)
    }
}