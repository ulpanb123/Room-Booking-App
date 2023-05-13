package com.example.jusanbookingapp.domain.models

import android.os.Parcelable
import android.util.Log
import kotlinx.parcelize.Parcelize
@Parcelize
data class Reservation(
    val reservationID : Int,
    val timeslots : List<TimeSlot>,
    val roomNumber: String
) : Parcelable
{
    @Parcelize
    data class TimeSlot(
        val start : Long,
        val end : Long,
        val userID : Long,
        val timeslotID : Long,
        val purpose : String
    ) : Parcelable
}