package com.example.jusanbookingapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterRequest(
    val email : String,
    val username : String,
    val password : String
) : Parcelable

@Parcelize
data class LoginRequest(
    val username: String,
    val password: String
) : Parcelable

@Parcelize
data class GetReservationsRequest(
    val start : Long,
    val end : Long
) : Parcelable

@Parcelize
data class AddTimeSlotRequest(
    val timeslots : List<Timeslot>
) : Parcelable {
    @Parcelize
    data class Timeslot(
        val start : Long,
        val end : Long,
        val purpose : String
    ) : Parcelable
}


