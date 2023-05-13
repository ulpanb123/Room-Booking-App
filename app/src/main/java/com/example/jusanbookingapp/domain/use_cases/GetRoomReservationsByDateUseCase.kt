package com.example.jusanbookingapp.domain.use_cases

import com.example.jusanbookingapp.domain.models.Reservation
import com.example.jusanbookingapp.domain.repositories.ReservationRepository

class GetRoomReservationsByDateUseCase(private val reservationRepository: ReservationRepository) {
    suspend operator fun invoke(roomNumber : String, startTime : Long, endTime : Long) : List<Reservation> {
        return reservationRepository.getRoomReservationsByDate(roomNumber = roomNumber, startTime = startTime, endTime = endTime)
    }
}