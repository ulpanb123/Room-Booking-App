package com.example.jusanbookingapp.data.repositories

import com.example.jusanbookingapp.data.network.ApiService
import com.example.jusanbookingapp.domain.models.Room
import com.example.jusanbookingapp.domain.repositories.RoomRepository

class RoomRepositoryImpl(private val api : ApiService) :  RoomRepository{
    override suspend fun getAllRooms(): List<Room> {
        return api.getAllRooms()
    }

    override suspend fun getRoomDetails(roomNumber: String): Room {
        return api.getRoomDetails(roomNumber)
    }
}