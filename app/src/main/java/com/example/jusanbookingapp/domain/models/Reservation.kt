package com.example.jusanbookingapp.domain.models

import android.os.Parcelable
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
        val start : Int,
        val end : Int,
        val userID : Int,
        val purpose : String
    ) : Parcelable
}