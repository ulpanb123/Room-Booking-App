package com.example.jusanbookingapp.presentation.profile.userReservations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jusanbookingapp.domain.models.Reservation
import com.example.jusanbookingapp.domain.models.UserBookingInfo
import com.example.jusanbookingapp.domain.use_cases.DeleteReservationUseCase
import com.example.jusanbookingapp.domain.use_cases.GetRoomDetailsUseCase
import com.example.jusanbookingapp.domain.use_cases.GetUserReservationsUseCase
import com.example.jusanbookingapp.presentation.utils.extensions.convertMillisToDate
import kotlinx.coroutines.launch
import java.util.*

class UserReservationsViewModel(
    private val getUserReservationsUseCase: GetUserReservationsUseCase,
    private val getRoomDetailsUseCase: GetRoomDetailsUseCase,
    private val deleteReservationUseCase: DeleteReservationUseCase
) : ViewModel() {
    private val _userReservations = MutableLiveData<List<Reservation>>()
    val userReservations : LiveData<List<Reservation>> = _userReservations

    private val _isDataReady = MutableLiveData<Boolean>()
    val isDataReady : LiveData<Boolean> = _isDataReady

    init {
        getUserReservs()
        _isDataReady.postValue(false)
    }

    private fun getUserReservs() {
        viewModelScope.launch {
            val list = getUserReservationsUseCase()
            _userReservations.postValue(list)
        }
    }

    fun convertInfo(reservations : List<Reservation>) : List<UserBookingInfo> {
        val newList : MutableList<UserBookingInfo> = mutableListOf()
        viewModelScope.launch{
            reservations.forEach {
                val room = getRoomDetailsUseCase(it.roomNumber)
                it.timeslots.forEach { timeslot ->
                    val startTime = timeslot.start
                    val endTime = timeslot.end

                    val format = "yyyy-MM-dd HH:mm"
                    val startString = Date(startTime).convertMillisToDate(format)
                    val endString = Date(endTime).convertMillisToDate(format)

                    val tmp1 = startString.split(" ")
                    val tmp2 = endString.split(" ")

                    newList.add(UserBookingInfo(
                        room = room,
                        bookingDate = tmp1[0],
                        timeSlot = "${tmp1[1]}-${tmp2[1]}",
                        timeslotID = timeslot.timeslotID
                        ))
                }
            }
            _isDataReady.postValue(true)
        }
        return newList
    }

    fun deleteReservation(roomNumber : String, timeslotID : Long) {
        viewModelScope.launch {
            deleteReservationUseCase(roomNumber, timeslotID)
        }
    }

    // TODO: Implement the ViewModel
}