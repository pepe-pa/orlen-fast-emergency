package com.hackyeah.orlen.medicalPoints.repository

import com.google.android.gms.maps.model.LatLng

data class MedicalPoint(
    val id: Int,
    val type: Type,
    val localization: LatLng,
    val name: String,
    val desc: String
) {

    enum class Type {
        HOSPITAL, AED
    }
}