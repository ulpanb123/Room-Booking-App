package com.example.jusanbookingapp.presentation.rooms.roomDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jusanbookingapp.domain.models.Reservation
import com.example.jusanbookingapp.domain.models.Room
import com.example.jusanbookingapp.domain.use_cases.AddReservationUseCase
import com.example.jusanbookingapp.domain.use_cases.DeleteReservationUseCase
import com.example.jusanbookingapp.domain.use_cases.GetRoomDetailsUseCase
import com.example.jusanbookingapp.domain.use_cases.GetRoomReservationsByDateUseCase
import kotlinx.coroutines.launch

class RoomDetailsViewModel(
    private val roomNumber: String,
    private val getRoomDetailsUseCase: GetRoomDetailsUseCase,
    private val getRoomReservationsByDateUseCase: GetRoomReservationsByDateUseCase,
    private val addReservationUseCase: AddReservationUseCase,
    private val deleteReservationUseCase: DeleteReservationUseCase
) : ViewModel()
{
    private val _currRoom = MutableLiveData<Room>()
    val currRoom: LiveData<Room> = _currRoom

    val errorMessage = MutableLiveData<String>()

    private val _eventsForDate = MutableLiveData<List<Reservation.TimeSlot>>()
    val eventsForDate = _eventsForDate

    private val _isAdded = MutableLiveData<Boolean>()
    val isAdded = _isAdded

    init {
        loadRoom()
        _isAdded.postValue(false)
    }

    private fun loadRoom() {
        viewModelScope.launch {
            val room = getRoomDetailsUseCase(roomNumber)
            _currRoom.postValue(room)
        }
    }

    fun getReservationByDate(start : Long, end : Long) {
        viewModelScope.launch {
            val reservations = getRoomReservationsByDateUseCase(roomNumber, startTime = start, endTime = end)
            if(reservations.isNotEmpty())
                _eventsForDate.postValue(reservations[0].timeslots)
            else
                _eventsForDate.postValue(emptyList())
        }
    }

    fun addReservation(start : Long, end : Long, purpose : String) {
        viewModelScope.launch {
            try {
                addReservationUseCase(roomNumber, start, end, purpose)
                _isAdded.postValue(true)
            } catch (t : Throwable) {
                onError(t.message!!)
            }
        }
    }

    fun deleteReservation(roomNumber : String, timeslotID : Long) {
        viewModelScope.launch {
            deleteReservationUseCase(roomNumber, timeslotID)
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        Log.d("RoomDetailsViewModel", message)
    }
}