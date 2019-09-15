package com.hackyeah.orlen.medicalPoints.di

import com.hackyeah.orlen.medicalPoints.repository.MedicalPointsRepository
import com.hackyeah.orlen.medicalPoints.repository.MedicalPointsRepositoryImpl
import com.hackyeah.orlen.medicalPoints.viewModel.MedicalPointsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val medicalPointsModule = module {
    single<MedicalPointsRepository> { MedicalPointsRepositoryImpl() }
    viewModel { MedicalPointsViewModel(get(), get()) }
}