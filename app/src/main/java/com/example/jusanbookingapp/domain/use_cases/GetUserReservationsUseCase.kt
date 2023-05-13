package com.example.jusanbookingapp.domain.use_cases

import com.example.jusanbookingapp.domain.models.Reservation
import com.example.jusanbookingapp.domain.repositories.ReservationRepository

class GetUserReservationsUseCase(private val reservationRepository: ReservationRepository) {
    suspend operator fun invoke() : List<Reservation> {
        return reservationRepository.getUserReservations()
    }
}