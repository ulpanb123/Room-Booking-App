package com.example.jusanbookingapp.domain.use_cases

import com.example.jusanbookingapp.domain.models.Room
import com.example.jusanbookingapp.domain.repositories.RoomRepository

class GetAllRoomsUseCase(private val roomRepository: RoomRepository) {
    suspend operator fun invoke() : List<Room> {
        return roomRepository.getAllRooms()
    }
}