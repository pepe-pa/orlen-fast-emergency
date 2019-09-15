package com.hackyeah.orlen.system

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import com.google.android.gms.location.LocationServices
import kotlin.math.absoluteValue

private const val GRAVITY = 9.81f
private const val SPEED_THRESHOLD = 10
private const val ACCELERATION_THRESHOLD = 5 // Emulator: ~5+ / Device: ~120+

private typealias CrashCallback = () -> Unit

object CrashAlert {

    fun subscribe(context: Context, callback: CrashCallback) {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        val listener = object : SensorEventListener {

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit

            override fun onSensorChanged(event: SensorEvent?) {
                event ?: return
                val acceleration = event.values.sum().absoluteValue - GRAVITY
                if (acceleration > ACCELERATION_THRESHOLD) {
                    LocationServices.getFusedLocationProviderClient(context)
                        .lastLocation.addOnSuccessListener { location: Location ->
                        if (location.speed > SPEED_THRESHOLD) callback.invoke()
                    }
                }
            }
        }

        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_FASTEST)
    }
}
