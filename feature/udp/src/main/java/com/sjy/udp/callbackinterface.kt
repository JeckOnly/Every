package com.sjy.udp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.concurrent.thread
import kotlin.coroutines.resume

/**
 * Created by JeckOnly on 2023/9/22
 * Describe: One shot callback to Coroutine suspend style
 */

interface AsyncCallback {
    fun onSuccess(data: String)
    fun onFailed(error: String)
}

object AsyncCallbackService {

    fun readCharacteristic(
        param: Int,
        callback: AsyncCallback
    ) {
        thread {
            try {
                // 模拟异步调用
                Thread.sleep(1000)
                throw IllegalStateException("test")
                callback.onSuccess("$param success")
            } catch (e: Exception) {
                callback.onFailed("$param failed")
            }

        }
    }

    /**
     * 调用端失败时收到异常
     */
    suspend fun awaitReadCharacteristic(param: Int) =
        suspendCancellableCoroutine<String> { continuation ->
            val callback = object : AsyncCallback {
                override fun onSuccess(data: String) {
                    continuation.resumeWith(Result.success(data))
                }

                override fun onFailed(error: String) {
                    continuation.resumeWith(Result.failure(Exception(error)))
                }
            }

            readCharacteristic(param, callback)
        }

    /**
     * 调用端失败时不收到异常，而是非法值指示发生了错误
     */
    suspend fun awaitReadCharacteristic2(param: Int) =
        suspendCancellableCoroutine<String> { continuation ->
            val callback = object : AsyncCallback {
                override fun onSuccess(data: String) {
                    continuation.resumeWith(Result.success(data))
                }

                override fun onFailed(error: String) {
                    continuation.resumeWith(Result.success(error))
                }
            }

            readCharacteristic(param, callback)
        }

}


fun main() {
//    AsyncCallbackService.readCharacteristic(1, object : AsyncCallback {
//        override fun onSuccess(data: String) {
//            println("onSuccess: $data")
//        }
//
//        override fun onFailed(error: String) {
//            println("onFailed: $error")
//        }
//    })

//    CoroutineScope(Dispatchers.Default).launch {
//        try {
//            val result = AsyncCallbackService.awaitReadCharacteristic(1)
//            println("result: $result")
//        } catch (e: Exception) {
//            println("error: ${e.message}")
//        }
//    }

    CoroutineScope(Dispatchers.Default).launch {
        try {
            val result = AsyncCallbackService.awaitReadCharacteristic2(1)
            println("result: $result")
        } catch (e: Exception) {
            println("error: ${e.message}")
        }
    }

    Thread.sleep(999999)
}