package com.hackyeah.orlen

import android.app.Application
import com.hackyeah.orlen.common.locationModule
import com.hackyeah.orlen.common.showNotification
import com.hackyeah.orlen.firstaid.di.firstAidModule
import com.hackyeah.orlen.main.di.mainModule
import com.hackyeah.orlen.medicalPoints.di.medicalPointsModule
import com.hackyeah.orlen.reportAccident.di.reportAccidentModule
import com.hackyeah.orlen.resuscitation.di.resuscitationModule
import com.hackyeah.orlen.system.CrashAlert
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        registerDependencies()

        /*CrashAlert.subscribe(this) {
            showNotification("CRASH", "You're dead...")
//            sendSMS("123456789", "Hi. I'm dead")
        }*/
    }

    private fun registerDependencies() {

        val list = listOf(
            mainModule,
            firstAidModule,
            locationModule,
            reportAccidentModule,
            resuscitationModule,
            medicalPointsModule
        )

        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(list)
        }
    }
}
