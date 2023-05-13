package com.example.jusanbookingapp.domain.repositories

import com.example.jusanbookingapp.data.network.ApiService
import com.example.jusanbookingapp.domain.models.Reservation

interface ReservationRepository {
    suspend fun getRoomReservationsByDate(roomNumber: String, startTime : Long, endTime : Long) : List<Reservation>

    suspend fun getUserReservations() : List<Reservation>

    suspend fun addReservation(roomNumber: String, start : Long, end : Long, purpose : String)

    suspend fun deleteReservation(roomNumber : String, timeslotID : Long)

}