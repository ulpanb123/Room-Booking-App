package com.example.jusanbookingapp.presentation.utils

fun interface ClickListener<T : Any> {
    fun onClick(item: T)
}