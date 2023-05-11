package com.example.jusanbookingapp.domain.repositories

import com.example.jusanbookingapp.domain.models.Room

interface RoomRepository {
    suspend fun getAllRooms() : List<Room>

    suspend fun getRoomDetails(roomNumber : String) : Room
}