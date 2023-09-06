package com.sjy.widget

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Created by JeckOnly on 2023/9/6
 * Describe:
 */
class Timer {

    private val _nowTimeInSeconds: MutableStateFlow<Int> = MutableStateFlow(0)
    val nowTimeInSeconds: StateFlow<Int> = _nowTimeInSeconds

    private var job: Job? = null

    fun start() {
        Log.d("Jeck", "$this start: ${_nowTimeInSeconds.value}")
        job = CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                delay(1000)
                _nowTimeInSeconds.update {
                    it + 1
                }
            }
        }
    }

    fun reset() {
        job?.cancel()
        _nowTimeInSeconds.update {
            0
        }
    }

}