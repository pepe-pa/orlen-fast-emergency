package com.hackyeah.orlen.resuscitation.di

import com.hackyeah.orlen.resuscitation.repository.ResuscitationApi
import com.hackyeah.orlen.resuscitation.repository.ResuscitationRepository
import com.hackyeah.orlen.resuscitation.viewModel.ResuscitationViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val resuscitationModule = module {

    viewModel {
        ResuscitationViewModel(get())
    }

    factory {
        ResuscitationRepository(get())
    }

    factory {
        ResuscitationApi()
    }
}