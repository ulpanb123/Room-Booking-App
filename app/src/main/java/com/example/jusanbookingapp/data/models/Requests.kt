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
    val start : Int,
    val ent : Int
) : Parcelable


