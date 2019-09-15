package com.hackyeah.orlen.reportAccident.di

import com.hackyeah.orlen.reportAccident.viewModel.ReportEmergencyViewModel
import org.koin.dsl.module

val reportAccidentModule = module {
    single {
        ReportEmergencyViewModel(get())
    }
}