package com.example.jusanbookingapp.data.network

import com.example.jusanbookingapp.data.models.GetReservationsRequest
import com.example.jusanbookingapp.data.models.LoginRequest
import com.example.jusanbookingapp.data.models.LoginResponse
import com.example.jusanbookingapp.data.models.RegisterRequest
import com.example.jusanbookingapp.domain.models.Reservation
import com.example.jusanbookingapp.domain.models.Room
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("register")
    suspend fun registerUser(
        @Body param: RegisterRequest
    )

    @POST("login")
    suspend fun login(
        @Body param: LoginRequest
    ): LoginResponse

    @GET("room")
    suspend fun getAllRooms(): List<Room>

    @GET("room/{roomNumber}")
    suspend fun getRoomDetails(@Path("roomNumber") roomNumber: String): Room

    @POST("reservation/date/{roomNumber}")
    suspend fun getRoomReservationsByDate(
        @Path("roomNumber") roomNumber: String,
        @Body param : GetReservationsRequest
    ) : List<Reservation>

}