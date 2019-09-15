package com.hackyeah.orlen.firstaid

import com.hackyeah.orlen.firstaid.repository.tts.TtsService
import io.reactivex.schedulers.Schedulers

class TtsEngine(private val ttsService: TtsService) {

    fun speakText(text: String) = ttsService.speakText(text).subscribeOn(Schedulers.newThread())
}