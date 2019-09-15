package com.hackyeah.orlen.medicalPoints.view

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*
import com.hackyeah.orlen.R
import com.hackyeah.orlen.common.observeOnce
import com.hackyeah.orlen.common.view.BaseActivity
import com.hackyeah.orlen.medicalPoints.repository.MedicalPoint
import com.hackyeah.orlen.medicalPoints.viewModel.MedicalPointsViewModel
import kotlinx.android.synthetic.main.report_emergency_layout.*
import org.koin.android.viewmodel.ext.android.viewModel

class MedicalPointsActivity : BaseActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    override val layoutRes: Int = R.layout.activity_medical_points

    override val viewModel: MedicalPointsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mapView.getMapAsync(this)
        mapView.onCreate(null)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        mapView.onLowMemory()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.setOnMarkerClickListener(this)
        googleMap?.mapType = GoogleMap.MAP_TYPE_NORMAL
        googleMap?.isMyLocationEnabled = true
        googleMap?.uiSettings?.apply {
            isMyLocationButtonEnabled = true
            isZoomControlsEnabled = true
            isZoomGesturesEnabled = true
        }

        viewModel.currentLocationLiveData.observeOnce {
            googleMap?.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(it.latitude, it.longitude),
                    CAMERA_ZOOM_LEVEL
                )
            )
        }

        viewModel.nearbyMedicalPoints.observe(this::getLifecycle) {
            googleMap?.addMarkers(it)
        }
    }

    private fun GoogleMap.addMarkers(markers: List<MedicalPoint>) {
        markers.forEach { marker ->
            val iconId = when (marker.type) {
                MedicalPoint.Type.AED -> R.drawable.ic_aed
                MedicalPoint.Type.HOSPITAL -> R.drawable.ic_hospital
            }
            val icon = getIconFromRes(iconId)

            val options = MarkerOptions()
                .position(marker.localization)
                .title(marker.name)
                .icon(icon)

            addMarker(options).apply {
                tag = marker.id
            }
        }
    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        val id = marker?.tag as? Int ?: return false
        val medicalPoint = viewModel.nearbyMedicalPoints.value?.get(id)

        // TODO mark on list / show desc

        return false
    }

    private fun getIconFromRes(@DrawableRes id: Int): BitmapDescriptor {
        val vectorDrawable = ResourcesCompat.getDrawable(resources, id, null)
        val bitmap = Bitmap.createBitmap(
            vectorDrawable!!.intrinsicWidth,
            vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}

private const val CAMERA_ZOOM_LEVEL = 18f