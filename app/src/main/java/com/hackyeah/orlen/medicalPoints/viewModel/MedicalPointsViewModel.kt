package com.hackyeah.orlen.medicalPoints.viewModel

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.hackyeah.orlen.common.repository.LocationRepository
import com.hackyeah.orlen.common.viewmodel.RxViewModel
import com.hackyeah.orlen.medicalPoints.repository.MedicalPoint
import com.hackyeah.orlen.medicalPoints.repository.MedicalPointsRepository

class MedicalPointsViewModel(
    locationRepository: LocationRepository,
    medicalPointsRepository: MedicalPointsRepository
) : RxViewModel() {

    private val _currentLocationLiveData = MutableLiveData<Location>()
    val currentLocationLiveData: LiveData<Location> = _currentLocationLiveData

    val nearbyMedicalPoints: LiveData<List<MedicalPoint>> =
        Transformations.map(_currentLocationLiveData) {
            medicalPointsRepository.getNearbyMedicalPoints(it)
        }

    init {
        locationRepository.currentLocation.subscribe({
            _currentLocationLiveData.value = it
        }, {}).register()
    }
}