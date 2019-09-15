package com.hackyeah.orlen.main.view

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.hackyeah.orlen.R
import com.hackyeah.orlen.common.view.BaseActivity
import com.hackyeah.orlen.firstaid.view.FirstAidActivity
import com.hackyeah.orlen.main.viewmodel.MainViewModel
import com.hackyeah.orlen.medicalPoints.view.MedicalPointsActivity
import com.hackyeah.orlen.reportAccident.view.ReportEmergencyBottomSheetDialog
import com.hackyeah.orlen.resuscitation.view.ResuscitationActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    override val viewModel: MainViewModel by viewModel()

    override val layoutRes = R.layout.activity_main

    private var bottomSheetDialogFragment: ReportEmergencyBottomSheetDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firstAid.setOnClickListener {
            startActivity(Intent(this, FirstAidActivity::class.java))
        }

        report.setOnClickListener {
            bottomSheetDialogFragment = ReportEmergencyBottomSheetDialog(this).also { it.show() }
        }

        nearby.setOnClickListener {
            startActivity(Intent(this, MedicalPointsActivity::class.java))
        }

        resuscitation.setOnClickListener {
            startActivity(Intent(this, ResuscitationActivity::class.java))
        }


    }


    override fun onResume() {
        super.onResume()
        bottomSheetDialogFragment?.onResume()
    }

    override fun onPause() {
        bottomSheetDialogFragment?.onPause()
        super.onPause()
    }

    override fun onStart() {
        super.onStart()
        bottomSheetDialogFragment?.onActivityStart()
    }

    override fun onStop() {
        bottomSheetDialogFragment?.onActivityStop()
        super.onStop()
    }

    override fun onLowMemory() {
        bottomSheetDialogFragment?.onLowMemory()
        super.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        bottomSheetDialogFragment?.onSaveInstanceState()
    }

    override fun onDestroy() {
        bottomSheetDialogFragment?.onDestroy()
        super.onDestroy()
    }

}
