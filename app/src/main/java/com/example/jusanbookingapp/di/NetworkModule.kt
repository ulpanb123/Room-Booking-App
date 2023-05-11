package com.example.jusanbookingapp.di

import com.example.jusanbookingapp.constants.AppPreferences
import com.example.jusanbookingapp.constants.BASE_URL
import com.example.jusanbookingapp.data.network.ApiService
import com.example.jusanbookingapp.data.network.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    factory<HttpLoggingInterceptor> {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        interceptor
    }

    factory<AuthInterceptor> {
        AuthInterceptor()}

    factory<OkHttpClient> {
        val httpLoggingInterceptor: HttpLoggingInterceptor = get()
        val authInterceptor: AuthInterceptor = get()
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()

        okHttpClient
    }

    factory {
        GsonConverterFactory.create()
    }

    factory<ApiService> {
        val okHttpClient: OkHttpClient = get()
        val gsonConverterFactory: GsonConverterFactory = get()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(ApiService::class.java)
    }
}