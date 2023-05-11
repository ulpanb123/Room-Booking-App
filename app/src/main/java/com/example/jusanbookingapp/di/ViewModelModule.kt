package com.example.jusanbookingapp.di

import com.example.jusanbookingapp.presentation.auth.login.LoginViewModel
import com.example.jusanbookingapp.presentation.auth.registration.RegistrationViewModel
import com.example.jusanbookingapp.presentation.rooms.RoomsViewModel
import com.example.jusanbookingapp.presentation.rooms.roomDetails.RoomDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RegistrationViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RoomsViewModel(get()) }
    viewModel { (roomNumber: String) ->
        RoomDetailsViewModel(
            roomNumber = roomNumber,
            getRoomDetailsUseCase = get(),
            getRoomReservationsByDateUseCase = get()
        )
    }
}