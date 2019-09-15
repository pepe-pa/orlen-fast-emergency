package com.hackyeah.orlen.firstaid.repository

import com.hackyeah.orlen.R

class InstructionsApi {


    private val instructions = listOf(
        Instruction(R.string.step_1, R.drawable.stop),
        Instruction(R.string.step_2, R.drawable.lights),
        Instruction(R.string.step_3, R.drawable.vest),
        Instruction(R.string.step_4, R.drawable.triangle),
        Instruction(R.string.step_5, R.drawable.first_aid_kit),
        Instruction(R.string.step_6, R.drawable.come),
        Instruction(R.string.step_7, R.drawable.accident),
        Instruction(R.string.step_8, R.drawable.emergency_call, AdditionalAction.CallEmergency(R.string.main_report_title))
    )

    val instructionCount: Int = instructions.size

    fun getInstruction(index: Int) = instructions.getOrNull(index)
}