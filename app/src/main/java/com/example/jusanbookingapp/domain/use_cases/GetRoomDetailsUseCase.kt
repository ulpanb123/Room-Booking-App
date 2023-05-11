package com.example.jusanbookingapp.domain.use_cases

import com.example.jusanbookingapp.domain.models.Room
import com.example.jusanbookingapp.domain.repositories.RoomRepository

class GetRoomDetailsUseCase(private val roomRepository: RoomRepository) {

    suspend operator fun invoke(roomNumber: String) : Room {
        return roomRepository.getRoomDetails(roomNumber)
    }
}