package com.hackyeah.orlen.firstaid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hackyeah.orlen.common.NavigationAction
import com.hackyeah.orlen.common.viewmodel.RxViewModel
import com.hackyeah.orlen.firstaid.TtsEngine
import com.hackyeah.orlen.firstaid.repository.AdditionalAction
import com.hackyeah.orlen.firstaid.repository.Instruction
import com.hackyeah.orlen.firstaid.repository.InstructionsRepository

class FirstAidViewModel(
    private val ttsEngine: TtsEngine,
    private val instructionsRepository: InstructionsRepository
) : RxViewModel() {

    private val _instructionLiveData: MutableLiveData<Instruction> = MutableLiveData()
    val instructionsLiveData: LiveData<Instruction> = _instructionLiveData

    private val _isLastInstruction = MutableLiveData<Boolean>()
    val isLastInstruction: LiveData<Boolean> = _isLastInstruction

    private val _navigationAction = MutableLiveData<NavigationAction>()
    val navigationAction: LiveData<NavigationAction> = _navigationAction

    fun playTTS(text: String) {
        ttsEngine.speakText(text)
            .subscribe()
            .register()
    }

    fun getNextInstruction() {
        _instructionLiveData.value = instructionsRepository.getNext()
        _isLastInstruction.value = instructionsRepository.isLastInstruction()
    }

    fun handleAdditionalAction(additionalAction: AdditionalAction) {
        when (additionalAction) {
            is AdditionalAction.CallEmergency -> _navigationAction.value = NavigationAction.ReportEmergencyBottomSheet
        }
    }
}