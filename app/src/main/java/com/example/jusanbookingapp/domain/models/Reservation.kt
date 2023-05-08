package com.example.jusanbookingapp.domain.models

data class Reservation(
    val date : String,
    val timeslot : String,
    val room : Room
)