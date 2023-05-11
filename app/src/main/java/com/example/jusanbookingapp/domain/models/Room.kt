package com.example.jusanbookingapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(
    val roomNumber : String,
    val floor : String,
    val area : String,
    val capacity : String,
    val description : String,
    val facilities : List<Facility>,
    val images : List<Image>
)  : Parcelable
{
    @Parcelize
    data class Facility(
        val name : String
    ) : Parcelable

    @Parcelize
    data class Image(
        val url : String
    ) : Parcelable

}

