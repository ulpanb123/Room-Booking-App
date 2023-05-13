package com.example.jusanbookingapp.di

import com.example.jusanbookingapp.domain.use_cases.*
import org.koin.dsl.module

val useCaseModule = module{
    factory { RegisterUseCase(get())}
    factory { LoginUseCase(get()) }
    factory { GetAllRoomsUseCase(get()) }
    factory { GetRoomDetailsUseCase(get()) }
    factory { GetRoomReservationsByDateUseCase(get()) }
    factory { GetUserReservationsUseCase(get()) }
    factory { AddReservationUseCase(get()) }
    factory { DeleteReservationUseCase(get())}
}