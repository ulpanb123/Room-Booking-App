package com.example.jusanbookingapp.data.network

import com.example.jusanbookingapp.data.models.*
import com.example.jusanbookingapp.domain.models.Reservation
import com.example.jusanbookingapp.domain.models.Room
import retrofit2.http.*

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

    @GET("reservation/my")
    suspend fun getUserReservations() : List<Reservation>

    @PATCH("reservation/{roomNumber}")
    suspend fun addReservation(
        @Path("roomNumber") roomNumber: String,
        @Body param : AddTimeSlotRequest
    )

    @DELETE("reservation/{roomNumber}/{timeslotID}")
    suspend fun deleteReservation(
        @Path("roomNumber") roomNumber: String,
        @Path("timeslotID") timeslotID : Long)

}