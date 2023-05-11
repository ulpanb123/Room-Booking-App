package com.example.jusanbookingapp.presentation.rooms.roomDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jusanbookingapp.domain.models.Reservation
import com.example.jusanbookingapp.domain.models.Room
import com.example.jusanbookingapp.domain.use_cases.GetRoomDetailsUseCase
import com.example.jusanbookingapp.domain.use_cases.GetRoomReservationsByDateUseCase
import kotlinx.coroutines.launch

class RoomDetailsViewModel(
    private val roomNumber: String,
    private val getRoomDetailsUseCase: GetRoomDetailsUseCase,
    private val getRoomReservationsByDateUseCase: GetRoomReservationsByDateUseCase
) : ViewModel()
{
    private val _currRoom = MutableLiveData<Room>()
    val currRoom: LiveData<Room> = _currRoom

    private val _eventsForDate = MutableLiveData<List<Reservation>>()
    val eventsForDate = _eventsForDate

    init {
        loadRoom()
    }

    private fun loadRoom() {
        viewModelScope.launch {
            val room = getRoomDetailsUseCase(roomNumber)
            _currRoom.postValue(room)
        }
    }

    fun getReservationByDate(start : Int, end : Int) {
        viewModelScope.launch {
            val events = getRoomReservationsByDateUseCase(roomNumber, startTime = start, endTime = end)
            _eventsForDate.postValue(events)
        }
    }
}