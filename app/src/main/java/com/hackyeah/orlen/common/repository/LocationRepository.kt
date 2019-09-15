package com.hackyeah.orlen.common.repository

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.location.LocationServices
import io.reactivex.Single

class LocationRepository(private val context: Context, private val geocoder: Geocoder) {

    val currentLocation: Single<Location>
        get() = Single.create {
            LocationServices.getFusedLocationProviderClient(context)
                .lastLocation.addOnSuccessListener { location: Location ->
                it.onSuccess(location)
            }
        }

    val currentAddress: Single<Address>
        get() = currentLocation.map {
            geocoder.getFromLocation(it.latitude, it.longitude, MAX_RESULT).first()
        }
}

private const val MAX_RESULT = 1