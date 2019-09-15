package com.hackyeah.orlen.resuscitation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hackyeah.orlen.common.viewmodel.RxViewModel
import com.hackyeah.orlen.resuscitation.repository.Resuscitation
import com.hackyeah.orlen.resuscitation.repository.ResuscitationRepository
import com.hackyeah.orlen.resuscitation.repository.ResuscitationScenario

class ResuscitationViewModel(private val resuscitationRepository: ResuscitationRepository) : RxViewModel() {
    private val _resuscitationSteps = MutableLiveData<List<Resuscitation>>()
    val resuscitationSteps: LiveData<List<Resuscitation>> = _resuscitationSteps

    private val _resuscitationLink = MutableLiveData<Int>()
    val resuscitationLink: LiveData<Int> = _resuscitationLink

    fun selectScenario(resuscitationScenario: ResuscitationScenario) {
        _resuscitationSteps.value = resuscitationRepository.getStepsFor(resuscitationScenario)
        _resuscitationLink.value = resuscitationRepository.getLinkFor(resuscitationScenario)
    }
}