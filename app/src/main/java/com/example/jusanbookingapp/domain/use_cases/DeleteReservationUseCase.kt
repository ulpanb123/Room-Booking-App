package com.example.jusanbookingapp.domain.use_cases

import com.example.jusanbookingapp.domain.repositories.ReservationRepository

class DeleteReservationUseCase(private val reservationRepository: ReservationRepository) {

    suspend operator fun invoke(roomNumber : String, timeslotID : Long) {
        reservationRepository.deleteReservation(roomNumber, timeslotID)
    }
}