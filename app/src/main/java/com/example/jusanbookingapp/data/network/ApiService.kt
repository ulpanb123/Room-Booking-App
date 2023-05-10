package com.example.jusanbookingapp.data.network

import com.example.jusanbookingapp.constants.AppPreferences
import com.example.jusanbookingapp.domain.models.User
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://singularity-booking-production.up.railway.app/"

private val logging = HttpLoggingInterceptor()
    .setLevel(HttpLoggingInterceptor.Level.BASIC)

private val authInterceptor = AuthInterceptor(AppPreferences.accessToken?:"")

private val okHttpBuilder = okhttp3.OkHttpClient.Builder()
    .addInterceptor(authInterceptor)
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .addInterceptor(logging)

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(okHttpBuilder.build())
    .build()

interface ApiService {

    @POST("register")
    suspend fun registerUser(
        @Body param: User
    )

}