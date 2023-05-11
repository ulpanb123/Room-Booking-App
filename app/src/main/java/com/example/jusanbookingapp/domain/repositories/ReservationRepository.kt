package com.example.jusanbookingapp.domain.repositories

import com.example.jusanbookingapp.data.network.ApiService
import com.example.jusanbookingapp.domain.models.Reservation

interface ReservationRepository {
    suspend fun getRoomReservationsByDate(roomNumber: String, startTime : Int, endTime : Int) : List<Reservation>
}