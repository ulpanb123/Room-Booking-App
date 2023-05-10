package com.example.jusanbookingapp.data.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val token: String) : okhttp3.Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // Add authorization header to the request
        val modifiedRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()

        return chain.proceed(modifiedRequest)
    }
}
