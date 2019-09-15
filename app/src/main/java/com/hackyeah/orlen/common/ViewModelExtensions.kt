package com.hackyeah.orlen.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeOnce(onValue: (T) -> Unit) {
    observeForever(object : Observer<T> {
        override fun onChanged(value: T) {
            onValue(value)
            removeObserver(this)
        }
    })
}