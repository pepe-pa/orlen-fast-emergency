package com.hackyeah.orlen.firstaid.view

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.lifecycle.Observer
import com.hackyeah.orlen.R
import com.hackyeah.orlen.common.NavigationAction
import com.hackyeah.orlen.common.view.BaseActivity
import com.hackyeah.orlen.firstaid.repository.Instruction
import com.hackyeah.orlen.firstaid.viewmodel.FirstAidViewModel
import com.hackyeah.orlen.reportAccident.view.ReportEmergencyBottomSheetDialog
import kotlinx.android.synthetic.main.activity_first_aid.*
import org.koin.android.viewmodel.ext.android.viewModel

class FirstAidActivity : BaseActivity() {
    override val layoutRes: Int = R.layout.activity_first_aid
    override val viewModel: FirstAidViewModel by viewModel()

    private val bottomSheetDialogFragment:ReportEmergencyBottomSheetDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setClickListeners()
        observeLiveData()

        viewModel.getNextInstruction()
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

    private fun setClickListeners() {
        playAgainButton.setOnClickListener {
            viewModel.playTTS(instructionTextView.text.toString())
        }

        nextButton.setOnClickListener {
            viewModel.getNextInstruction()
        }
    }

    private fun observeLiveData() {
        viewModel.instructionsLiveData.observe(this, Observer { onNewInstruction(it) })

        viewModel.isLastInstruction.observe(this, Observer { isLastInstruction ->
            nextButton.visibility = if (isLastInstruction) View.GONE else View.VISIBLE
        })

        viewModel.navigationAction.observe(this, Observer { navigationAction ->
            additionalAction.setOnClickListener {
                when(navigationAction) {
                    NavigationAction.ReportEmergencyBottomSheet -> ReportEmergencyBottomSheetDialog(this).show()
                }
            }

        })
    }

    private fun onNewInstruction(instruction: Instruction) {
        image.setImageResource(instruction.image)
        instructionTextView.setText(instruction.text)
        additionalAction.visibility = if(instruction.additionalAction == null) View.GONE else View.VISIBLE

        instruction.additionalAction?.let {
            additionalAction.setText(it.text)
            viewModel.handleAdditionalAction(it)
        }
        viewModel.playTTS(instructionTextView.text.toString())
    }
}
