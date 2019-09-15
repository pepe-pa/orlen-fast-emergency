package com.hackyeah.orlen.resuscitation.repository

import com.hackyeah.orlen.R

class ResuscitationApi {

    fun getChildrenSteps(): List<Resuscitation> {
        return listOf(
            Resuscitation(1, R.string.resuscitation_child_step_1, R.drawable.c1),
            Resuscitation(2, R.string.resuscitation_child_step_2, R.drawable.c2),
           Resuscitation(3, R.string.resuscitation_child_step_3, R.drawable.c3),
           Resuscitation(4, R.string.resuscitation_child_step_4, R.drawable.c2),
           Resuscitation(5, R.string.resuscitation_child_step_5, R.drawable.a4)
        )
    }

    fun getBabySteps(): List<Resuscitation> {
        return listOf(
            Resuscitation(1, R.string.resuscitation_baby_step_1, R.drawable.b1),
            Resuscitation(1, R.string.resuscitation_baby_step_2, R.drawable.b2),
            Resuscitation(1, R.string.resuscitation_baby_step_3, R.drawable.b3),
            Resuscitation(1, R.string.resuscitation_baby_step_4, R.drawable.b2),
            Resuscitation(1, R.string.resuscitation_baby_step_5, R.drawable.a4)
        )
    }

    fun getAdultSteps(): List<Resuscitation> {
        return listOf(
            Resuscitation(1, R.string.resuscitation_adult_step_1, R.drawable.a1),
            Resuscitation(2, R.string.resuscitation_adult_step_2, R.drawable.a2),
            Resuscitation(3, R.string.resuscitation_adult_step_3, R.drawable.a3),
            Resuscitation(4, R.string.resuscitation_adult_step_4, R.drawable.a4)
        )
    }

}
