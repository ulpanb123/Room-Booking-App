package com.example.jusanbookingapp.data.repositories

import com.example.jusanbookingapp.data.models.GetReservationsRequest
import com.example.jusanbookingapp.data.network.ApiService
import com.example.jusanbookingapp.domain.models.Reservation
import com.example.jusanbookingapp.domain.repositories.ReservationRepository

class ReservationRepositoryImpl(private val api : ApiService)  : ReservationRepository {
    override suspend fun getRoomReservationsByDate(
        roomNumber: String,
        startTime: Int,
        endTime: Int
    ): List<Reservation> {
        val request = GetReservationsRequest(startTime, endTime)
        return api.getRoomReservationsByDate(roomNumber = roomNumber, request)
    }
}