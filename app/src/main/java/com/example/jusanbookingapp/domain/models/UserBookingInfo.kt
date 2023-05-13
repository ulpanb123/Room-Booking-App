package com.example.jusanbookingapp.domain.models

data class UserBookingInfo(
    val room : Room,
    val bookingDate : String,
    val timeSlot : String,
    val timeslotID : Long
)