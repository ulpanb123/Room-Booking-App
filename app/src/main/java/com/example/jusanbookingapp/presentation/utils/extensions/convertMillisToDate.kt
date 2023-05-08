package com.example.jusanbookingapp.presentation.utils.extensions

import java.util.*
import java.text.SimpleDateFormat

fun Date.convertMillisToDate(format: String): String {
    val formatter = SimpleDateFormat(format, Locale.getDefault())
    return formatter.format(this)
}
