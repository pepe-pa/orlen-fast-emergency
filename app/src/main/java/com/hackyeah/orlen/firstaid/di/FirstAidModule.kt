package com.hackyeah.orlen.firstaid.di

import android.speech.tts.TextToSpeech
import com.hackyeah.orlen.firstaid.TtsEngine
import com.hackyeah.orlen.firstaid.repository.InstructionsApi
import com.hackyeah.orlen.firstaid.repository.InstructionsRepository
import com.hackyeah.orlen.firstaid.repository.tts.TtsService
import com.hackyeah.orlen.firstaid.viewmodel.FirstAidViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val firstAidModule = module {

    viewModel {
        FirstAidViewModel(get(), get())
    }

    factory {
        TtsEngine(get())
    }

    factory {
        TtsService(androidContext())
    }


    factory {
        InstructionsRepository(get())
    }

    factory {
        InstructionsApi()
    }
}