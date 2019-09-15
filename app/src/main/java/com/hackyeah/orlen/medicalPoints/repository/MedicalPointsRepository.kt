package com.hackyeah.orlen.medicalPoints.repository

import android.location.Location
import com.google.android.gms.maps.model.LatLng

interface MedicalPointsRepository {
    fun getNearbyMedicalPoints(location: Location): List<MedicalPoint>
}

class MedicalPointsRepositoryImpl : MedicalPointsRepository {

    // TODO add points to mock
    private val mock = listOf(

        MedicalPoint(
            0,
            MedicalPoint.Type.HOSPITAL,
            LatLng(52.210242, 20.987820),
            "HOSPITAL",
            "desc 0"
        ),

        MedicalPoint(
            1,
            MedicalPoint.Type.AED,
            LatLng(52.210342, 20.987880),
            "AED",
            "desc 1"
        )
    )

    override fun getNearbyMedicalPoints(location: Location): List<MedicalPoint> = mock
}