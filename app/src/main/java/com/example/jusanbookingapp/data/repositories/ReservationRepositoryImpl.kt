package com.example.jusanbookingapp.data.repositories

import com.example.jusanbookingapp.data.models.AddTimeSlotRequest
import com.example.jusanbookingapp.data.models.GetReservationsRequest
import com.example.jusanbookingapp.data.network.ApiService
import com.example.jusanbookingapp.domain.models.Reservation
import com.example.jusanbookingapp.domain.repositories.ReservationRepository

class ReservationRepositoryImpl(private val api : ApiService)  : ReservationRepository {
    override suspend fun getRoomReservationsByDate(
        roomNumber: String,
        startTime: Long,
        endTime: Long
    ): List<Reservation> {
        val request = GetReservationsRequest(startTime, endTime)
        return api.getRoomReservationsByDate(roomNumber = roomNumber, request)
    }

    override suspend fun getUserReservations(): List<Reservation> {
        return api.getUserReservations()
    }

    override suspend fun addReservation(roomNumber: String, start: Long, end: Long, purpose: String) {
        val timeslots : List<AddTimeSlotRequest.Timeslot> = listOf(
            AddTimeSlotRequest.Timeslot(
                start = start,
                end = end,
                purpose = purpose
        ))
        val request = AddTimeSlotRequest(
            timeslots
        )
        api.addReservation(roomNumber, request)
    }

    override suspend fun deleteReservation(roomNumber: String, timeslotID: Long) {
        api.deleteReservation(roomNumber, timeslotID)
    }
}