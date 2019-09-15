package com.hackyeah.orlen.reportAccident.viewModel

import android.location.Address
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hackyeah.orlen.common.repository.LocationRepository
import com.hackyeah.orlen.common.viewmodel.RxViewModel

class ReportEmergencyViewModel(private val locationRepository: LocationRepository) : RxViewModel() {

    private val _currentLocationLiveData = MutableLiveData<Location>()
    val currentLocationLiveData: LiveData<Location> = _currentLocationLiveData

    private val _currentAddressLiveData = MutableLiveData<Address>()
    val currentAddressLiveData = _currentAddressLiveData

    fun getCurrentLocationAndAddress() {
        locationRepository.currentLocation.
                subscribe({
                    _currentLocationLiveData.value = it
                }, {})
            .register()

        locationRepository.currentAddress
            .subscribe({
                _currentAddressLiveData.value = it
            },{})
            .register()
    }
}