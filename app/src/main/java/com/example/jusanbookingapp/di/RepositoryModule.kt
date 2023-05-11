package com.example.jusanbookingapp.di

import com.example.jusanbookingapp.data.repositories.AuthRepositoryImpl
import com.example.jusanbookingapp.data.repositories.ReservationRepositoryImpl
import com.example.jusanbookingapp.data.repositories.RoomRepositoryImpl
import com.example.jusanbookingapp.domain.repositories.AuthRepository
import com.example.jusanbookingapp.domain.repositories.ReservationRepository
import com.example.jusanbookingapp.domain.repositories.RoomRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<AuthRepository> { AuthRepositoryImpl(get()) }
    factory<RoomRepository> { RoomRepositoryImpl(get()) }
    factory<ReservationRepository> { ReservationRepositoryImpl(get()) }
}