package com.example.jusanbookingapp.data.network

import com.example.jusanbookingapp.constants.AppPreferences
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : okhttp3.Interceptor {

    val token = AppPreferences.accessToken

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        if(token.isNullOrEmpty()) {
            return chain.proceed(originalRequest)
        }

        // Add authorization header to the request
        val modifiedRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()

        return chain.proceed(modifiedRequest)
    }
}
