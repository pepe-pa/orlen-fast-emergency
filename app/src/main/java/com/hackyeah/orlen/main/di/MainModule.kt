package com.hackyeah.orlen.main.di

import com.hackyeah.orlen.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val mainModule : Module = module {

    // ViewModel instance of MyViewModel
    // get() will resolve Repository instance
    viewModel { MainViewModel() }

    // Single instance of Repository
    // EXAMPLE:
    //viewModel { MyViewModel() }
    //single<Repository> { MyRepository() }
}