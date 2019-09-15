package com.hackyeah.orlen.common

import android.location.Geocoder
import com.hackyeah.orlen.common.repository.LocationRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.util.Locale

val locationModule = module {
    factory {
        LocationRepository(androidContext(), get())
    }

    single {
        Geocoder(androidContext(), Locale.getDefault())
    }
}