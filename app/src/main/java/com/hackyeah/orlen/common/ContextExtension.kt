package com.hackyeah.orlen.common

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import com.hackyeah.orlen.R

fun Context.showNotification(title: String, message: String) {
    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val pIntent = PendingIntent.getActivity(this, 0, Intent(), 0)
    val notification = Notification.Builder(this)
        .setContentTitle(title)
        .setContentText(message)
        .setContentIntent(pIntent)
        .setSmallIcon(R.drawable.ic_car)
        .build()

    notificationManager.notify(0, notification)
}

fun Context.sendSMS(phoneNo: String, msg: String) {
    try {
        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(phoneNo, null, msg, null, null)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

}

