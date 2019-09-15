package com.hackyeah.orlen.reportAccident.view

import android.content.Context
import android.content.Intent
import android.location.Address
import android.net.Uri
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hackyeah.orlen.R
import com.hackyeah.orlen.common.observeOnce
import com.hackyeah.orlen.reportAccident.viewModel.ReportEmergencyViewModel
import kotlinx.android.synthetic.main.report_emergency_layout.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class ReportEmergencyBottomSheetDialog(context: Context) : BottomSheetDialog(context), KoinComponent, OnMapReadyCallback {

    private val viewModel: ReportEmergencyViewModel by inject()

    init {
        setContentView(R.layout.report_emergency_layout)
        callEmergencyButton.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$EMERGENCY_NUMBER")
            context.startActivity(callIntent)
        }

        viewModel.getCurrentLocationAndAddress()

        viewModel.currentAddressLiveData.observeOnce {
            addressTextView.text = buildAddressText(it)
        }

        mapView.getMapAsync(this)

        setOnShowListener {
            mapView.onCreate(null)
            mapView.onStart()
            mapView.onResume()
        }
    }

    fun onResume() {
        mapView?.onResume()
    }

    fun onActivityStart() {
        mapView?.onStart()
    }

    fun onActivityStop() {
        mapView?.onStop()
    }

    fun onPause() {
        mapView?.onPause()
    }

    fun onDestroy() {
        mapView?.onDestroy()
    }

    fun onLowMemory() {
        mapView?.onLowMemory()
    }

    override fun onSaveInstanceState(): Bundle {

        return super.onSaveInstanceState().also {
            mapView?.onSaveInstanceState(it)
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.mapType = GoogleMap.MAP_TYPE_NORMAL
        googleMap?.isMyLocationEnabled = true
        googleMap?.uiSettings?.apply {
            isMyLocationButtonEnabled = true
            isZoomControlsEnabled = true
            isZoomGesturesEnabled = true
        }

        viewModel.currentLocationLiveData.observeOnce {
            googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(
                LatLng(it.latitude, it.longitude),
                CAMERA_ZOOM_LEVEL
            ))
        }
    }

    private fun buildAddressText(address: Address): String {
        return address.getAddressLine(0)
            .plus(NEW_LINE)
            .plus("${address.subAdminArea} ${address.adminArea}")
            .plus(NEW_LINE)
            .plus("${address.latitude}, ${address.longitude}")
    }
}

private const val CAMERA_ZOOM_LEVEL = 18F
private const val EMERGENCY_NUMBER = 1117777
private const val NEW_LINE = "\n"