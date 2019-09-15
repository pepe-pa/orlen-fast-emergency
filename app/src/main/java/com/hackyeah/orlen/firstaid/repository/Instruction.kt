package com.hackyeah.orlen.firstaid.repository

data class Instruction(
    val text: Int,
    val image: Int,
    val additionalAction: AdditionalAction? = null
)

sealed class AdditionalAction(
    val text: Int
) {
    class CallEmergency(text: Int) : AdditionalAction(text)
}