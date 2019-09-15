package com.hackyeah.orlen.firstaid.repository.tts

import android.content.Context
import android.speech.tts.TextToSpeech
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor

class TtsService(context: Context) {

    private var isInitializedProcessor = BehaviorProcessor.create<Boolean>()

    private var isInitializedChanges: Flowable<Boolean> = isInitializedProcessor.filter { true }

    private val textToSpeech: TextToSpeech = TextToSpeech(context) {
        isInitializedProcessor.offer(true)
    }

    fun speakText(text: String): Completable =
        isInitializedChanges
            .flatMapCompletable {
                Completable.fromAction {
                    textToSpeech.speak(
                        text,
                        TextToSpeech.QUEUE_FLUSH,
                        null,
                        text
                    )
                }
            }
}