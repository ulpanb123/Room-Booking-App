package com.example.jusanbookingapp.domain.use_cases

import com.example.jusanbookingapp.domain.repositories.ReservationRepository

class AddReservationUseCase(private val reservationRepository: ReservationRepository) {
    suspend operator fun invoke(roomNumber : String, start : Long, end : Long, purpose : String) {
        reservationRepository.addReservation(roomNumber, start, end, purpose)
    }
}