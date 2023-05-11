package com.example.jusanbookingapp.presentation.rooms

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jusanbookingapp.domain.models.Room
import com.example.jusanbookingapp.domain.use_cases.GetAllRoomsUseCase
import kotlinx.coroutines.launch

class RoomsViewModel(private val getAllRoomsUseCase: GetAllRoomsUseCase) : ViewModel() {
    private val _rooms = MutableLiveData<List<Room>>()
    val rooms: LiveData<List<Room>> = _rooms
    init {
        getRooms()
    }

    private fun getRooms() {
        viewModelScope.launch {
            val roomsList = getAllRoomsUseCase()
            _rooms.postValue(roomsList)
            Log.e("VM", roomsList.toString())
        }
    }

}