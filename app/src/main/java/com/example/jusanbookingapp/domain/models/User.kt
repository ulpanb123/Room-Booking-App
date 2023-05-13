package com.example.jusanbookingapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val token : String,
    val userID : Int
) : Parcelable
