package com.hackyeah.orlen.resuscitation.repository

import com.hackyeah.orlen.R

class ResuscitationRepository(private val resuscitationApi: ResuscitationApi) {

    fun getStepsFor(resuscitationScenario: ResuscitationScenario): List<Resuscitation> {
        return when (resuscitationScenario) {
            ResuscitationScenario.ADULT -> resuscitationApi.getAdultSteps()
            ResuscitationScenario.CHILD -> resuscitationApi.getChildrenSteps()
            ResuscitationScenario.BABY -> resuscitationApi.getBabySteps()
        }
    }

    fun getLinkFor(resuscitationScenario: ResuscitationScenario): Int {
        return when (resuscitationScenario) {
            ResuscitationScenario.ADULT -> R.string.additional_info_adult
            ResuscitationScenario.CHILD -> R.string.additional_info_child
            ResuscitationScenario.BABY ->R.string.additional_info_baby
        }
    }
}