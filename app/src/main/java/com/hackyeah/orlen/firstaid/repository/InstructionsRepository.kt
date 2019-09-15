package com.hackyeah.orlen.firstaid.repository

class InstructionsRepository(private val instructionsApi: InstructionsApi) {

    private var currentIndex = 0

    fun getNext() = instructionsApi.getInstruction(currentIndex)?.also { currentIndex++ }

    fun isLastInstruction(): Boolean = instructionsApi.instructionCount <= currentIndex
}