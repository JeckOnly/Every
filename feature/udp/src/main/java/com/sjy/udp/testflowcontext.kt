package com.sjy.udp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by JeckOnly on 2023/9/22
 * Describe:
 */

object TestFlowContext {

    fun testFlowContext() {
        val flow = flow<String> {
            repeat(100) {
                Timber.d("emit $it in ${Thread.currentThread().name}")
                emit(it.toString())
            }
        }
            .flowOn(Dispatchers.Default)

        CoroutineScope(Dispatchers.Main).launch {
            flow.collect {
                Timber.d("collect $it in ${Thread.currentThread().name}")
                println(it)
            }
        }
    }
}

fun interface FlowCollector {
    suspend fun emit(value: String)
}

suspend fun main() {
    val f: suspend FlowCollector.() -> Unit = {
        emit("A")
        emit("B")
        emit("C")
    }

    FlowCollector { value -> print(value) }.f()

    object : FlowCollector {
        override suspend fun emit(value: String) {
            print(value)
        }
    }.f()

    f(FlowCollector { value -> print(value) })

    f(object : FlowCollector {
        override suspend fun emit(value: String) {
            print(value)
        }
    })

    f { print(it) } // ABC

}